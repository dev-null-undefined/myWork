import yfinance as yf
import datetime
import urllib.request
import json

download_url = 'ftp://ftp.nasdaqtrader.com/symboldirectory/nasdaqlisted.txt'
with urllib.request.urlopen(download_url) as r:
    data = r.read()

data = data.decode("utf-8")

data = data.split('\r\n')
tokens = []
for dat in data:
    token = ""
    for char in dat:
        if(char != '|'):
            token += char
        else:
            break
    tokens.append(token)
tokens = tokens[1:-1]
print("number of tokens: {0}".format(len(tokens)))

print("Filtering")
def enterpriseValue(token):
    ticker = yf.Ticker(token)
    if not isinstance(ticker, yf.ticker.Ticker):
        print("Not valid ticker: {0}".format(token))
        return 0
    else:
        try:
            x = ticker.info["marketCap"]
            if isinstance(x, int):
                print('getting value for {0} and return value is: {1}'.format(token, x))
                return -x
            else:
                print('getting value for {0} and return value is not int'.format(token))
                return 0
        except:
            print("Exception!! for token: {0}".format(token))
            return 0
tokens = sorted(tokens, key=lambda item1: enterpriseValue(item1))
tokens = tokens[0:49]
print("Done filtering")

print("Writing to file")
with open('PythonExport/tokens.txt', 'w') as file:
    for token in tokens:
        file.write(token+"\r\n")
print("Done writing")

today = datetime.date.today()
numberOfSteps = 5*12
stepSize = 365/12
intervalPresizion = '5d'

goodStocks = {}

for token in tokens:
    tickerData = yf.Ticker(token)
    stepAvgs =[]
    actualNumberOfSteps = 0
    for step in range(1, numberOfSteps):
        startDate = today - datetime.timedelta((numberOfSteps-step)*stepSize)
        endDate = today - datetime.timedelta((numberOfSteps-step-1)*stepSize)
        try:
            tickerDf = tickerData.history(interval=intervalPresizion, start="{0}".format(startDate), end="{0}".format(endDate))
            avg, colms = 0, 0
            for row in tickerDf.index:
                avg += (tickerDf.loc[row, 'High']+tickerDf.loc[row, 'Low'])/2
                colms += 1
                #print(tickerDf.loc[ row , 'High' ], end= "\n")
            stepAvgs.append(avg/colms)
            #print("Mounth:{0},sum:{1},avg:{2},colms{3},startDate:{4},endDate:{5}.".format(step, avg, avg/colms, colms, startDate, endDate))
            actualNumberOfSteps += 1
        except:
            print("Some Error")
    avgUp, numberOfAvgUp = 0, 0
    for i in range(1, len(stepAvgs)):
        avgUp += stepAvgs[i]-stepAvgs[i-1]
        numberOfAvgUp += 1
    if numberOfAvgUp==0:
        goodStocks[token] = 0
        print("Division by 0 Token: {0}.".format(token))
    else:
        goodStocks[token] = avgUp/numberOfAvgUp
        print("Token: {0},avgUp {1}".format(token, avgUp/numberOfAvgUp))

print(goodStocks)
for x, y in goodStocks.items():
    print(x+"|", y)

goodStocksExport = {'exDict': goodStocks}
with open('PythonExport/file.json', 'w') as file:
    file.write(json.dumps(goodStocksExport))

goodStocksSorted=sorted(goodStocks.items(), key = lambda kv:kv[1])
goodStocksExport = {'exDict': goodStocksSorted}
with open('PythonExport/fileSorted.json', 'w') as file:
    file.write(json.dumps(goodStocksExport))

   
import json
import os

import pymysql
db = pymysql.connect("localhost", "testuser", "test123", "TESTDB")
cursor = db.cursor()

dirPath = input("Enter dir to scan: ")
for file in os.listdir(dirPath):
    print(os.path.join(dirPath, file))
    file = open(os.path.join(dirPath, file), "r")
    contents = file.read()
    record = json.loads(contents)
    print(record)

db.close()

using System;
using System.Collections.Generic;
using System.Text;

namespace NeuralNetwork
{
    public class Node
    {
        public List<double> weight = new List<double>();
        public double nodeWeight;
        Random rand = new Random();
        public Node(List<double> weight, double nodeWeight)
        {
            this.weight = weight;
            this.nodeWeight = nodeWeight;
        }
        public Node(Node nw)
        {
            nodeWeight = nw.nodeWeight;
            weight = new List<double>();
            for (int i = 0; i < nw.weight.Count; i++)
            {
                weight.Add(nw.weight[i]);
            }
        }
        public Node(int numberOfConnections)
        {   
            for(int i = 0; i < numberOfConnections; i++)
            {
                weight.Add(rand.NextDouble()-0.5 * 2.0);
            }
            nodeWeight = (rand.NextDouble() - 0.5)*2.0;
        }
        public double getValue(List<double> numbers)
        {
            double value = 0;
            for (int i=0;i< numbers.Count;i++)
            {
                value += numbers[i] * weight[i];
            }
            return value * nodeWeight;
        }
        public void addToWeights(List<double> numbers)
        {
            for (int i = 0; i < numbers.Count; i++)
            {
                weight[i] = Math.Max(-1,Math.Min(1, weight[i] + numbers[i]));
            }
        }
        public List<double> generateRandomNums(double multiplaer)
        {
            List<double> random = new List<double>();
            for (int i = 0; i < weight.Count; i++)
            {
                random.Add((rand.NextDouble() - 0.5) * 2 * multiplaer);
            }
            return random;
        }
        public void addRandomNums(double multiplaer)
        {
            List<double> random = new List<double>();
            for (int i = 0; i < weight.Count; i++)
            {
                random.Add((rand.NextDouble() - 0.5) * 2 * multiplaer);
            }
            addToWeights(random);
        }
        public override string ToString()
        {
            string value = "\"weight\":"+ nodeWeight+ ",\n\"weights\":[";
            for (int i = 0; i < weight.Count; i++)
            {
                if (i != 0)
                {
                    value += ",";
                }
                value += "{\"id_" + i + "\":" + weight[i] + "}";
            }
            value += "]";
            return value;

        }

        internal void setRandomNums(double v)
        {
            List<int> numbers = new List<int>();
            for (int i = 0; i < weight.Count; i++)
            {
                numbers.Add(i);
            }
            for (int i = 0; i < weight.Count*(1-v); i++)
            {
                int randomIndex = (int) rand.NextDouble() * numbers.Count;
                int number=numbers[randomIndex];
                numbers.RemoveAt(randomIndex);
                weight[number] = (rand.NextDouble() - 0.5) * 2;
            }
        }
    }
}

using System;
using System.Collections.Generic;
using System.Text;

namespace NeuralNetwork
{
    public class NeuralNetwork
    {
        public Node finalNode;
        public List<List<Node>> layer = new List<List<Node>>();
        public double score;
        public NeuralNetwork(Node final, List<List<Node>> layers)
        {
            finalNode=final;
            layer = layers;
        }
        public NeuralNetwork(NeuralNetwork nw)
        {
            finalNode = new Node(nw.finalNode);
            for (int i = 0; i < nw.layer.Count; i++)
            {
                layer.Add(new List<Node>());
                for (int o = 0; o < nw.layer[i].Count; o++)
                {
                    layer[i].Add(new Node(nw.layer[i][o]));
                }
            }
        }
        public NeuralNetwork(int numberOfLayers,int numberOfInputs)
        {
            finalNode = new Node(numberOfInputs);
            for(int i= 0; i < numberOfLayers; i++)
            {
                layer.Add(new List<Node>());
                for (int o = 0; o < numberOfInputs; o++)
                {
                    layer[i].Add(new Node(numberOfInputs));
                }
            }
        }
        public double resoult(List<double> numbers)
        {
            return resoultLayer(numbers, 0);
        }
        public double resoultLayer(List<double> numbers,int layerNumber)
        {
            if (layerNumber <= layer.Count - 1)
            {
                for (int i = 0; i < numbers.Count; i++)
                {
                    numbers[i]=layer[layerNumber][i].getValue(numbers);
                }
                return resoultLayer(numbers, layerNumber + 1);
            }
            else
            {
                return finalNode.getValue(numbers);
            }
        }
        public override string ToString()
        {
            string value = "{\"Main\":{" + finalNode.ToString() + "},\n\"Layers\":[";
            for(int i = 0; i < layer.Count; i++)
            {
                if (i != 0)
                {
                    value += ",";
                }
                value += "{ \"LayerNumber\":" + i + ",\n\"Nodes\":[\n";
                for(int o = 0; o < layer[i].Count; o++)
                {
                    if (o != 0)
                    {
                        value += ",\n";
                    }
                    value += "{\n" + layer[i][o].ToString() + "\n}";
                }
                value += "]}\n";
            }
            return value + "]}";
        }

        internal void setRandomToWeights(double v)
        {
            for (int i = 0; i < layer.Count; i++)
            {
                for (int o = 0; o < layer[i].Count; o++)
                {
                    layer[i][o].setRandomNums(v);
                }
            }
        }

        public void addRadnomToWeights(double multiplaer)
        {
            for (int i = 0; i < layer.Count; i++)
            {
                for (int o = 0; o < layer[i].Count; o++)
                {
                    layer[i][o].addRandomNums(multiplaer);
                }
            }
        }
    }
}

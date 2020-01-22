using System;
using System.Collections.Generic;
using System.Text;

namespace NeuralNetwork
{
    public class AiLearning
    {
        public List<NeuralNetwork> networks = new List<NeuralNetwork>();
        public int numberOfNetworks,numberOfLayers, numberOfInputs;
        public AiLearning(int numberOfSteps, int numberOfLayers, int numberOfInputs)
        {
            numberOfNetworks = numberOfSteps;
            this.numberOfLayers = numberOfLayers;
            this.numberOfInputs = numberOfInputs;
            for (int i = 0; i < numberOfNetworks; i++)
            {
                networks.Add(new NeuralNetwork(numberOfLayers, numberOfInputs));
            }
        }
        public void setScores(List<Double> scores)
        {
            for (int i = 0; i < numberOfNetworks; i++)
            {
                networks[i].score = scores[i];
            }
        }
        public void defaultLearn(int newOnes)
        {
            networks.Sort((a, b) => a.score.CompareTo(b.score));
            double best = networks[0].score;
            for (int i = 1; i < numberOfNetworks; i++)
            {
                if (i + newOnes < numberOfNetworks)
                {
                    networks[i].addRadnomToWeights(networks[i].score / best);
                }
                else
                {
                    networks[i] = new NeuralNetwork(numberOfLayers, numberOfInputs);
                }
            }
        }
        public void defaultLearn(int newOnes,int copiesOfTheBest)
        {
            networks.Sort((a, b) => b.score.CompareTo(a.score));
            double best = networks[0].score;
            for (int i = 1; i < numberOfNetworks; i++)
            {
                if (i + newOnes + copiesOfTheBest < numberOfNetworks)
                {
                    double score = networks[i].score;
                    networks[i].addRadnomToWeights(score/best);
                }
                else
                {
                    if(i + copiesOfTheBest < numberOfNetworks)
                    {
                        networks[i] = new NeuralNetwork(networks[0]);
                        networks[i].addRadnomToWeights(0.05);
                    }
                    else
                    {
                        networks[i] = new NeuralNetwork(numberOfLayers, numberOfInputs);
                    }
                }
            }
        }
        public double advancedLearn(int newOnes,int copiesOfTheBest)
        {
            networks.Sort((a, b) => b.score.CompareTo(a.score));
            double best = networks[0].score;
            for (int i = 1; i < numberOfNetworks; i++)
            {
                if (i + newOnes + copiesOfTheBest < numberOfNetworks)
                {
                    networks[i].setRandomToWeights(networks[i].score / best);
                }
                else
                {
                    if (i + copiesOfTheBest < numberOfNetworks)
                    {
                        networks[i] = new NeuralNetwork(networks[0]);
                        networks[i].addRadnomToWeights(0.5);
                    }
                    else
                    {
                        networks[i] = new NeuralNetwork(numberOfLayers, numberOfInputs);
                    }
                }
            }
            networks[0].addRadnomToWeights(0.02);
            return best;
        }
        public override string ToString()
        {
            string value = "{\"networks\":[";
            for (int i = 0; i < networks.Count; i++)
            {
                if (i != 0)
                {
                    value += ",";
                }
                value += "{\"network_" + i + "\":" + networks[i].ToString() + "}";
            }
            value += "]}";
            return value;

        }
    }
}

package com.passion;

public class FiringNeuron
{
	public Neuron Neuron;
	public double TotalInput;
        public double Output;
	
	public FiringNeuron (Neuron neuron)
       {
           Neuron = neuron;
       }
	public double Fire (double input1, double input2)
	{
          TotalInput =  input1 * Neuron.Weight1 + input2 * Neuron.Weight2 + Neuron.Bias;
	// Apply ReLU
	return Output = TotalInput >= 0 ? TotalInput : TotalInput / 100;
	}
	public void Learn (double input1, double input2, double expectedOutput, double learningRate)
	{
		Fire (input1, input2);
// The loss is (Output - expectedOutput) * (Output - expectedOutput) / 2
// When we derive this we get (Output - expectedOutput). We reverse the sign
	// because a positive gradient means we should move left and vice versa.
		double outputVotes = expectedOutput - Output;
		
	// Apply the chain rule: multiply by the slope of the ReLU function
		double slopeOfRelu = TotalInput >= 0 ? 1 : .01;		
		double inputVotes = outputVotes * slopeOfRelu;		
		
		double adjustment = inputVotes * learningRate;
		
		Neuron.Bias += adjustment;
		Neuron.Weight1 += adjustment * input1;
		Neuron.Weight2 += adjustment * input2;
	}
}

package com.passion;

import java.util.*; 
import java.util.stream.*;
public class Main
{
   public static void main(String[] args)
   {
     //Let's create some data
     int totalSamples = 1000;
     double learningRate = 0.02;
    
    Random  random = new Random();
    List<CustomInput> list = new ArrayList<CustomInput>();
    for (int i = 0; i < totalSamples ; i++)
    {
        int input1 = random.nextInt(2);
        int input2 = random.nextInt(2);
        int dout = input1 == 1 && input2 == 1 ? 0 :1;
        list.add(new CustomInput(input1, input2, dout)); 

    }//End of for loop
        
        /* for(CustomInput item : list)
        {
          System.out.println(item.input1 + ":" + item.input2 + ":" +   item.desiredoutput);
        } */
        int trainingCount = totalSamples * 8 / 10;
        int testingCount = totalSamples - trainingCount;
        List<CustomInput> trainingSet = list.stream().limit(trainingCount).collect(Collectors.toList());
	
List<CustomInput> testingSet = list.stream().skip(trainingCount).collect(Collectors.toList());

	Neuron  neuron = new Neuron();
	FiringNeuron firingNeuron = new FiringNeuron (neuron);
        
        for (CustomInput inputs : trainingSet) {
            firingNeuron.Learn (inputs.input1, inputs.input2, inputs.desiredoutput, learningRate);
         }
  List<CustomOutput> outputlist = new ArrayList<CustomOutput>();
        for (CustomInput inputs : testingSet) {
            double actout = firingNeuron.Fire(inputs.input1, inputs.input2);
            boolean b = (actout >= 0.5 ) == (inputs.desiredoutput == 1);
            double error = actout - inputs.desiredoutput;    
            CustomOutput co = new CustomOutput(actout, b, error);
            outputlist.add(co);
         }
       
	
	System.out.println("Final state of neuron");
        System.out.println("Weight 1: " + neuron.Weight1);
        System.out.println("Weight 2: " + neuron.Weight2);
        System.out.println("Bias: " + neuron.Bias);
	List<CustomOutput> results = outputlist.stream()
                    .filter(obj -> obj.success == true)
                    .collect(Collectors.toList());

       System.out.println("Successful: "+ results.size());
        	



    }//End of Main


}//End of class

class CustomInput
{
     public int input1;
     public int input2;
     public int desiredoutput;

     CustomInput(int a, int b, int c )
     {

       input1 = a;
       input2 = b;
       desiredoutput = c;

     }
     
}//End of CustomInput class


class CustomOutput
{
     public double  actualoutput;
     public boolean success;
     public double  error;

     CustomOutput(double  a, boolean  b, double  c )
     {

       actualoutput = a;
       success = b;
       error = c;

     }

}//End of CustomOutput class

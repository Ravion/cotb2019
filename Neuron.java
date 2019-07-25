package com.passion;

import java.util.*;

public class Neuron
{
	public double Weight1;
        public double  Weight2;
	public double Bias;

	public Neuron ()
	{
		Weight1 = GetSmallRandomNumber();
		Weight2 = GetSmallRandomNumber();
		Bias = GetSmallRandomNumber();
	}

	public static final  Random _random = new Random();
	static double GetSmallRandomNumber()
        {
               return (.0009 * _random.nextDouble() + .0001) * (_random.nextInt (2) == 0 ? -1 : 1);
         } 
}

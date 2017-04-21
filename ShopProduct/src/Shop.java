/**
 *
 * @author KUMAR Chandan
 * Date->21/04/2017
 * Purpose-> To fetch data from CSV file and give customer the shop having all the products given by customer as an input with minimum cost.
 */
    
   //package com.newgen.ingram;

    import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;

     public class Shop 
    {
    	 private static Logger logger = Logger.getLogger(Shop.class);
    	 private String inputFile;
  
    public void setInputFile(String inputFile) 
    {
        this.inputFile = inputFile;
    }
    public void delete_file(String inputFile)
    {
    	System.out.println("Deleting the corresponding file");
    	File inputWorkbook = new File(inputFile);
    	inputWorkbook.delete();
    }
   

    public void read(String products) throws IOException
    {
    	
        System.out.println("Reading CSV File");
        int count=0,match=1;

        try 
        {
        	File file = new File(inputFile);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line1,line2;
			String prod_name;Integer shop_id;Float price;
			int k=0;
			
			
			String[] prods=products.split(" ");
			int len_prods=prods.length;
		
			HashMap<Integer,Float> productshopMap=new HashMap<Integer,Float>();
			HashMap<Integer,Float> common_shopMap=new HashMap<Integer,Float>();
			HashMap<Integer,Integer> VisitedMap=new HashMap<Integer,Integer>();
			
			//Code snippet for checking headers are correct or not starts
			while ((line1 = bufferedReader.readLine()) != null) {
				stringBuffer.append(count);
				stringBuffer.append(line1);
				stringBuffer.append("\n");
				count++;
				//Code for knapsack starts
				if(!line1.equals(""))
				{
					if(count==1)
					{
						if(!line1.split(",")[0].equalsIgnoreCase("Shop_ID")
						|| !line1.split(",")[1].equalsIgnoreCase("Price") 
						|| !line1.split(",")[2].equalsIgnoreCase("Label"))
						{
							match=0;
							
						}
					}
			    }
				break;
			} 		
			//Code snippet for checking headers are correct or not ends
			if(match==1)
			{
			//Main loop that will run for all the product in input
			for(int i=0;i<len_prods;i++)
			{
				FileReader fileReader2 = new FileReader(file);
				BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
				count=0;
				
				//loop for traversing the file for every product
				while ((line2 = bufferedReader2.readLine()) != null)
				{
					
					stringBuffer.append(count);
					stringBuffer.append(line2);
					stringBuffer.append("\n");
					count++;
					if(count!=1)
					{
					if(!line2.equals(""))
					{
						shop_id=Integer.parseInt(line2.split(",")[0]);
			            price=Float.parseFloat(line2.split(",")[1]);
			            prod_name=line2.split(",")[2].trim().replace(" ", "");
			            
			            //Checking if the product column of the current line contains the product entered in input
			            if(prod_name.contains(prods[i]))
			            {
			            	
			            	if(i>=1)
			            	{
			            	//Condition for filling data in temporary map for the second,third,so on times	
			            	if(productshopMap.get(shop_id)!=null)
			            	{
			            		productshopMap.put(shop_id,price+productshopMap.get(shop_id));
			            		
			            		//If the current shop is visited for as many products as are there in the input then
			            		//this shop id will be entered in the common map where all the inputs of all
			            		//shops will be there which are having all the products provided in input.
			            		//After filling data in common map mark that shop visited again for further references.
			            		if(VisitedMap.get(shop_id)==i)
			            		{
			            		common_shopMap.put(shop_id, productshopMap.get(shop_id));
			            		VisitedMap.put(shop_id, i+1);
			            		}
			            		
			            	}
			            	}
			            	else if(i==0)
			            	{
			            		//condition for filling data in temporary map 
			            		//for the first time and marking that this shop was visited for the first time.
			            		productshopMap.put(shop_id,price);
			            		VisitedMap.put(shop_id, i+1);
			            	}
			            }
					}
					else
					{
						System.out.println("Blank values in file. Utility exiting");
						System.exit(0);
					}
					}
				}
				
			}
			}
			else
			{
				System.out.println("Headers not correct.Please enter correct Headers");
            	logger.debug("Headers not correct.Please enter correct Headers");
			}
			
			float min_sum=Float.MAX_VALUE;
			int shopid=0;
			
			//Checking if common map has entries or not.If yes then output the smallest sum of price.If not
			//then the products are in different shops.
			if(common_shopMap.size()!=0)
			{
			for(Map.Entry m:common_shopMap.entrySet()){    
				   if(Float.parseFloat(m.getValue().toString())<min_sum)
				   {
					   min_sum=Float.parseFloat(m.getValue().toString());
					   shopid=Integer.parseInt(m.getKey().toString());
				   }
				  }  
			
			System.out.println(shopid+" "+min_sum);
			}
			else
			{
				System.out.println("Products found in different shops not in same shop");
			}
			}
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        finally
        {
        	
        }
   
    
    }
    
    public static void main(String args[])
    {
    	Shop sh = new Shop();
    	GlobalVar.filepath="D:\\Kumar Chandan Updated resumes\\assignments\\Shop.csv";
    	sh.setInputFile(GlobalVar.filepath);
    	String products="johnson_wipes cotton_balls teddy_bear cotton_buds";
    	try {
			sh.read(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    }


    

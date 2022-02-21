package com.progressoft.tools;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;

public class myNormalizer implements Normalizer , ScoringSummary{

    ArrayList<String> arrayList1 = null;

    /**
     *
     * @param csvPath          path of CSV file to read
     * @param destPath         path to which the scaled CSV file should be written
     * @param colToStandardize the name of the column to normalize
     * @return
     */
    @Override
    public ScoringSummary zscore(Path csvPath, Path destPath, String colToStandardize)
    {
        try {
            if (colToStandardize == "salary") {
                String result = "";
                BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath.toString()));
                String[] arr = null;
                ArrayList<String> arrayList = new ArrayList<>();
                while ((result = bufferedReader.readLine()) != null)
                {
                    // use comma as separator
                    arr = result.split(",");
                    arrayList.add(arr[4]);
                }

                for(int i=1 ; i<arrayList.size() ; i++)
                {
                    System.out.print(arrayList.get(i) + "   ");
                }
                System.out.println("");
                System.out.println("");

                arrayList1 = arrayList;

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destPath.toString()));
                StringBuilder stringBuilder = new StringBuilder();

                // Append strings from array
                for (int i = 1; i < arrayList.size(); i++) {
                    stringBuilder.append(arrayList.get(i));
                    stringBuilder.append("\n");
                }
                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.close();
                bufferedReader.close();
            }

            else if (colToStandardize == "mark") {
                String result = "";
                BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath.toString()));
                String[] arr = null;
                ArrayList<String> arrayList = new ArrayList<>();
                while ((result = bufferedReader.readLine()) != null) {
                    // use comma as separator
                    arr = result.split(",");
                    arrayList.add(arr[3]);

                }
                for(int i=1 ; i<arrayList.size() ; i++)
                {
                    System.out.print(arrayList.get(i) + "  ");
                }
                System.out.println("");
                System.out.println("");

                arrayList1 = arrayList;

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destPath.toString()));
                StringBuilder stringBuilder = new StringBuilder();

               // Append strings from array
                for (int i = 1; i < arrayList.size(); i++) {
                    stringBuilder.append(arrayList.get(i));
                    stringBuilder.append("\n");
                }
                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.close();
                bufferedReader.close();
            }

        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ScoringSummary minMaxScaling(Path csvPath, Path destPath, String colToNormalize) {
        try {
             if (colToNormalize == "salary") {
                 String result = "";
                 BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath.toString()));
                 String[] arr = null;
                 ArrayList<String> arrayList = new ArrayList<>();
                 while ((result = bufferedReader.readLine()) != null) {

                    arr = result.split(",");
                    arrayList.add(arr[4]);
                 }

                 for(int i=1 ; i<arrayList.size() ; i++)
                  {
                    System.out.print(arrayList.get(i) + "  ");
                  }

                System.out.println("");
                System.out.println("");

                arrayList1 = arrayList;

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destPath.toString()));
                StringBuilder stringBuilder = new StringBuilder();

                // Append strings from array
                for (int i = 1; i < arrayList.size(); i++)
                 {
                    stringBuilder.append(arrayList.get(i));
                    stringBuilder.append("\n");
                 }

                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.close();
                bufferedReader.close();
               }

                else if (colToNormalize == "mark")
                {
                 String result = "";
                 BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath.toString()));
                 String[] arr = null;
                 ArrayList<String> arrayList = new ArrayList<>();
                 while ((result = bufferedReader.readLine()) != null)
                 {
                     arr = result.split(",");
                     arrayList.add(arr[2]);
                }

                for(int i=1 ; i<arrayList.size() ; i++)
                {
                    System.out.print(arrayList.get(i) + "  ");
                }
                System.out.println("");
                System.out.println();

                arrayList1 = arrayList;

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destPath.toString()));
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 1; i < arrayList.size(); i++) {
                    stringBuilder.append(arrayList.get(i));
                    stringBuilder.append("\n");
                }
                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.close();
                bufferedReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    ArrayList <String> finalArrayList = arrayList1;
    int sum1=0 ,sum2=0;
    double avg , avg2 , up;
    BigDecimal mean , std;
    @Override
    public BigDecimal mean() {
        for(int i = 0; i< finalArrayList.size() ; i++)
        {
            sum1 += Integer.parseInt(finalArrayList.get(i));
        }
        avg = sum1/finalArrayList.size();
        mean = BigDecimal.valueOf(avg);
        return mean;
    }

    @Override
    public BigDecimal standardDeviation() {

        for(int i = 0; i< finalArrayList.size() ; i++)
        {
            sum2 += (avg - Integer.parseInt(finalArrayList.get(i)));
        }
        up = sum2*sum2;
        avg2= (up/finalArrayList.size());

        double temp=0;
        double res = avg2/2;
        do{
            temp = res;
            res = (temp+(avg2/temp))/2;
        }while((temp-res)!=0);

        std = BigDecimal.valueOf(res);

        return std;
    }
    @Override
    public BigDecimal variance() {
        BigDecimal var;
        var = BigDecimal.valueOf(avg2);
        return var;
    }

    @Override
    public BigDecimal median() {
        if(finalArrayList.size()%2!=0)
        {
            int oddmedian = finalArrayList.size()/2;
            int med = Integer.parseInt(finalArrayList.get(oddmedian));
            BigDecimal median = BigDecimal.valueOf(med);
            return median;
        }
        else{
            int evenmedian = finalArrayList.size()/2;
            int med = (Integer.parseInt((finalArrayList.get(evenmedian-1)) + Integer.parseInt( finalArrayList.get(evenmedian))))/2;
            BigDecimal median = BigDecimal.valueOf(med);
            return median;
            //5 4 6 7 8 9 3 2 7 6     10/2 = 5
        }
    }

    @Override
    public BigDecimal min() {
        double minimum =Double.parseDouble(finalArrayList.get(0));
        for(int i=0 ; i<finalArrayList.size() ; i++)
        {
            if(Double.parseDouble(finalArrayList.get(i)) <= minimum)
                minimum = Double.parseDouble(finalArrayList.get(i));
        }
        BigDecimal min = BigDecimal.valueOf(minimum);
        return min;
    }

    @Override
    public BigDecimal max() {
        double maximum =Double.parseDouble(finalArrayList.get(0));
        for(int i=0 ; i<finalArrayList.size() ; i++)
        {
            if(Double.parseDouble(finalArrayList.get(i)) >= maximum)
                maximum = Double.parseDouble(finalArrayList.get(i));
        }
        BigDecimal max = BigDecimal.valueOf(maximum);
        return max;
    }
}
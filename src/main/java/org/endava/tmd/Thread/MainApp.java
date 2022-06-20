package org.endava.tmd.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*Write a program that uses multiple threads to find the integer in the range 1 to 100000
that has the largest number of divisors. The maximum number of threads to be used at the same time is 10.
Compare the results to a single threaded solution*/
class DivisorNumber implements Runnable{
    private int threadNumber;
    DivisorNumber(int i){ threadNumber = i;}
    public void run(){
        int num = 1, cnt, resultNum = 1, resultCnt = 1;

        while(num <= 100000){
            cnt = 0;
            for(int i = 2; i <= num / 2; i++){
                if(num % i == 0){
                    cnt++;
                }
            }

            if(cnt > resultCnt){
                resultNum = num;
                resultCnt = cnt;
            }

            num++;
        }

        System.out.println("Thread " + threadNumber + " : " + resultNum);
    }
}
public class MainApp {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        System.out.println("Numarul cu cei mai multi divizori in range 1-1000 este:");

       //8sec
        for(int i = 0; i < 11; i++){
            DivisorNumber divnum = new DivisorNumber(i);
            es.submit(divnum);
        }

        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        }catch (Exception e){}

/*
        //75ms
        int num = 1, cnt, resultNum = 1, resultCnt = 1;

        while(num != 1001){
            cnt = 0;
            for(int i = 2; i <= num / 2; i++){
                if(num % i == 0){
                    cnt++;
                }
            }

            if(cnt > resultCnt){
                resultNum = num;
                resultCnt = cnt;
            }

            num++;
        }

        System.out.println("Single thread: " + resultNum);
       */
    }
}

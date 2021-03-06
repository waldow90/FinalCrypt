/*
 * CC BY-NC-ND 4.0 2017 Ron de Jong (ronuitzaandam@gmail.com).
 *
 * This is free software; you can redistribute it 
 * under the terms of the Creative Commons License
 * Creative Commons License: (CC BY-NC-ND 4.0) as published by
 * https://creativecommons.org/licenses/by-nc-nd/4.0/ ; either
 * version 4.0 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0
 * International Public License for more details.
 *
 * You should have received a copy of the Creative Commons 
 * Public License License along with this software;
 */
package rdj;

import java.util.ArrayList;
import java.util.Arrays;

public class Stats
{
//  Files
    private long filesProcessed = 0;
    private long filesTotal = 0;
    
//  File Encrypting Bytes
    private long fileBytesProcessed = 0;
    private long fileBytesTotal = 0;

//  Files Encrypting Bytes
    private long filesBytesProcessed = 0;
    private long filesBytesTotal = 0;
    
//  File Encryption Time
    private long fileStartNanoTime = 0;
    private long fileEndNanoTime = 0;

//  Files Encryption Time
    private long filesStartNanoTime = 0;
    private long filesEndNanoTime = 0;
    private long nanoSeconds = 0;

    public Stats()
    {
        
    }
    
//  Getters

//  Files
    public long getFilesProcessed()						{ return filesProcessed; }
    public long getFilesTotal()							{ return filesTotal; }

//  File Encrypting Bytes
    public long getFileBytesProcessed()						{ return fileBytesProcessed; }
    public long getFileBytesTotal()						{ return fileBytesTotal; }

//  Files Encrypting Bytes
    public long getFilesBytesProcessed()					{ return filesBytesProcessed; }
    public long getFilesBytesTotal()						{ return filesBytesTotal; }

//  File Encryption Time
    public long getFileStartEpoch()						{ return fileStartNanoTime; }
    public long getFileEndEpoch()						{ return fileEndNanoTime; }

//  Files Encryption Time
    public long getAllDataStartEpoch()						{ return filesStartNanoTime; }
    public long getAllDataEndEpoch()						{ return filesEndNanoTime; }
    
//  Setters
    
//  Files
    public void setFilesProcessed(long filesProcessed)				{ this.filesProcessed = filesProcessed; }
    public void setFilesTotal(long filesTotal)					{ this.filesTotal = filesTotal; }

//  File Encrypting Bytes
    public void setFileBytesProcessed(long fileBytesProcessed)			{ this.fileBytesProcessed = fileBytesProcessed; }
    public void setFileBytesTotal(long fileBytesTotal)				{ this.fileBytesTotal = fileBytesTotal; }
        
//  Files Encrypting Bytes
    public void setAllDataBytesProcessed(long filesBytesProcessed)		{ this.filesBytesProcessed = filesBytesProcessed; }
    public void setAllDataBytesTotal(long filesBytesTotal)			{ this.filesBytesTotal = filesBytesTotal; }

//  File Encryption Time
    public void setFileStartNanoTime()						{ this.fileStartNanoTime = System.nanoTime(); }
    public void setFileEndNanoTime()						{ this.fileEndNanoTime = System.nanoTime(); }

//  Files Encryption Time
    public void setAllDataStartNanoTime()					{ this.filesStartNanoTime = System.nanoTime(); }
    public void setAllDataEndNanoTime()						{ this.filesEndNanoTime = System.nanoTime(); }
    public void clock()								{ this.nanoSeconds += (filesEndNanoTime - filesStartNanoTime); }
    
//  Adders
    
//  Files
    public void addFilesProcessed(long filesProcessed)				{ this.filesProcessed += filesProcessed; }
    public void addFilesTotal(long filesTotal)					{ this.filesTotal += filesTotal; }

//  File Encrypting Bytes
    public void addFileBytesProcessed(long fileBytesProcessed)			{ this.fileBytesProcessed += fileBytesProcessed; }
    public void subFileBytesProcessed(long fileBytesProcessed)			{ this.fileBytesProcessed -= fileBytesProcessed; }
    public void addFileBytesTotal(long fileBytesTotal)				{ this.fileBytesTotal += fileBytesTotal; }
        
//  Files Encrypting Bytes
    public void addAllDataBytesProcessed(String s, long filesBytesProcessed)    { this.filesBytesProcessed += filesBytesProcessed; /*System.out.println(s + " Added: " + filesBytesProcessed);*/ }
    public void addAllDataBytesTotal(long filesBytesTotal)			{ this.filesBytesTotal += filesBytesTotal; }

//  Stats
    
    
    
//  START
    public String getStartSummary(String mode)
    {
        String fileString = "files"; if (filesTotal == 1)			{ fileString = "file"; } else { fileString = "files"; }
        String returnString = "\r\nStarted " + mode + " " + filesTotal + " " + fileString + " totally " + getHumanSize(filesBytesTotal,1,"Bytes") + "\r\n\r\n";
        
        return returnString;
    }
    
    
    
    public String getAllDataBytesProgressPercentage()                               
    {
        String returnString = new String();
        double percentage = ((double)(filesBytesProcessed) / (double)(filesBytesTotal) * 100.0 ); // *1000 from mSec to Sec
	if (percentage > 100.0) { percentage = 100.0; }
//        String throughputString = String.format("%.1f", Math.floor(percentage));
        String throughputString = String.format("%.1f", percentage);
        returnString = throughputString + "%";
        
        return returnString;
    }
    
    
//  END
    public String getEndSummary(String mode)                               
    {
        String fileString = "files"; if (filesTotal == 1) { fileString = "file"; } else { fileString = "files"; }
        String returnString = "\r\nFinished " + mode + " [" + filesProcessed + " / " + filesTotal + "] " + fileString + " totally [" + getHumanSize(filesBytesProcessed, 1,"Bytes") + " / " + getHumanSize(filesBytesTotal ,1,"Bytes") + "] in " + getElapsedTime(nanoSeconds) + " " + getAllDataBytesThroughPut() + "\r\n\r\n";
        return returnString;
    }
    
    public String getElapsedTime(long nanosecs)
    {
//	long totalSecs = 197579; // Test
	long totalSecs = Double.valueOf((nanosecs)/1000000000.0).longValue();
	long days = totalSecs / (3600 * 24);
	long hours = totalSecs / 3600;
	long minutes = (totalSecs % 3600) / 60;
	long seconds = totalSecs % 60;

	String dayDesc = "";
	String duration = "";
	
	if (days == 1) { dayDesc = "day"; } else { dayDesc = "days"; }
	if (days>0) { duration = String.format("[%1d %s %02d:%02d:%02d]", days, dayDesc, hours, minutes, seconds); } else { duration = String.format("[%02d:%02d:%02d]", hours, minutes, seconds); }
	
	return duration;
    }
    
    public String getAllDataBytesThroughPut()                               
    {
        String returnString = new String();
        double throughput = ( ((double)(filesBytesProcessed) / (((double)nanoSeconds / 1000000000.0))) ); // *1000 from mSec to Sec
        String throughputString = String.format("%.1f", throughput);
        returnString = "(average: " + getHumanSize(throughput,1,"Bytes") + "/s)\r\n";
        
        return returnString;
    }
    
    public String getBruteForceThroughPut(long items, long nanosecs)                               
    {
        String returnString = new String();
        double throughput = ( ((double)(items) / (((double)nanosecs / 1000000000.0))) ); // *1000 from mSec to Sec
        String throughputString = String.format("%.0f", throughput);
//        returnString = "(average: " + getHumanSize(throughput,1,"Passwords") + "/s)\r\n";
        returnString = "(average: " + throughputString + " passwords/sec)\r\n";
        
        return returnString;
    }
    
    
//  OTHER
    public static String getHumanSize(double value,int decimals, String unit)
    {
        int x = 0;
        long factor;
        double newValue = value;
        String returnString = new String("");
        ArrayList<String> magnitude = new ArrayList<String>(); magnitude.addAll(Arrays.asList("Zi" + unit.charAt(0),"Ei" + unit.charAt(0),"Pi" + unit.charAt(0),"Ti" + unit.charAt(0),"Gi" + unit.charAt(0),"Mi" + unit.charAt(0),"Ki" + unit.charAt(0), unit));
        for (factor = 70; factor > 0; factor -= 10)
        {
            if ((value / Math.pow(2, factor)) >= 1) { newValue = (value / Math.pow(2, factor)); returnString = String.format("%.1f", (newValue)) + " " + magnitude.get(x); break; } x++;
        }
        if (factor == 0) { newValue = (value / Math.pow(2, factor)); returnString = String.format("%." + decimals + "f", (newValue)) + " " + magnitude.get(x); }
        return returnString;
    }
    
    public static String getDecimal(double value,int decimals) { return String.format("%." + decimals + "f", (value)); }
    
    public void reset()
    {
        filesProcessed = 0;
        filesTotal = 0;
        fileBytesProcessed = 0;
        fileBytesTotal = 0;
        filesBytesProcessed = 0;
        filesBytesTotal = 0;
        fileStartNanoTime = 0;
        fileEndNanoTime = 0;
        filesStartNanoTime = 0;
        filesEndNanoTime = 0;
        nanoSeconds = 0;
    }
    
//    public static void main(String[] args)
//    {
//        System.out.println(getHumanSize(12l));
//        System.out.println(getHumanSize(13000l));
//        System.out.println(getHumanSize(14000000l));
//        System.out.println(getHumanSize(15000000000l));
//        System.out.println(getHumanSize(16000000000000l));
//        System.out.println(getHumanSize(17000000000000000l));
//        System.out.println(getHumanSize(1800000000000000000l));
//    }
}

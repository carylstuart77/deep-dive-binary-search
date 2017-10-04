package edu.cnm.deepdive.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class Search {
  
  private static final String RESOURCE_BUNDLE_NAME = "usage3_french";
  //private static final String RESOURCE_BUNDLE_NAME = "usage";
  private static final String USAGE_MESSAGE_KEY = "searchMessage";
  private static final String PARSE_ERROR_MESSAGE_KEY = "parseErrorMessage";
  private static final String VALUE_ERROR_MESSAGE_KEY = "valueErrorMessage";
  private static final String READ_ERROR_MESSAGE_KEY = "haystackErrorMessage";
  private static final String FOUND_MESSAGE_KEY = "foundMessage";
  private static final String NOT_FOUND_MESSAGE_KEY = "notFoundMessage";
  

public static void main(String[] args) {
  try {
    ResourceBundle resources = getBundle(RESOURCE_BUNDLE_NAME);
    int needle = getSearchValue(args, resources);
    //Call to readValues
    Integer[] haystack = readValues(resources);
    int foundPosition = findValue(needle, haystack);
    if (foundPosition >= 0) {
      System.out.printf(resources.getString(FOUND_MESSAGE_KEY), 
          needle, foundPosition);
    }else {
      System.out.printf(resources.getString(NOT_FOUND_MESSAGE_KEY),
          needle, ~foundPosition);
    } 
  } catch (Exception ex) {
    //Do nothing.
  }
  
}

//get Search Values

private static int getSearchValue(String[] args, ResourceBundle resources)
  throws IllegalArgumentException, NumberFormatException, ArrayIndexOutOfBoundsException {
  try {
    int value = Integer.parseInt(args[0]);
    if (value < 0) {
      throw new IllegalArgumentException();
    }
    return value;
    
  } catch (NumberFormatException ex) {
    System.out.printf(resources.getString(PARSE_ERROR_MESSAGE_KEY));
    System.out.printf(resources.getString(USAGE_MESSAGE_KEY),
      Search.class.getName());
      throw ex;
      
  } catch (IllegalArgumentException ex) {
    System.out.printf(resources.getString(VALUE_ERROR_MESSAGE_KEY));
    System.out.printf(resources.getString(USAGE_MESSAGE_KEY), 
        Search.class.getName());
    throw ex;
    
  } catch (ArrayIndexOutOfBoundsException ex) {
    System.out.printf(resources.getString(USAGE_MESSAGE_KEY), 
        Search.class.getName());
    throw ex;
  }
} 

private static ResourceBundle getBundle(String bundleName) {
  return ResourceBundle.getBundle(bundleName);
}

//readValues; return integer type objects; 

private static Integer[] readValues(ResourceBundle resources) 
    throws NumberFormatException, IOException {  
  
  
  try (
      //takes an input stream from System.in;  Try with resources
      InputStreamReader reader = new InputStreamReader(System.in);
      //read a line at a time.
      BufferedReader buffer = new BufferedReader(reader);
  ) {
    //accumulate data;
    List<Integer> data = new LinkedList<>();
    //read a line at a time; test for end of file with null; update test body
    for (String line = buffer.readLine(); line != null; line = buffer.readLine()) {
      //body of for loop; valueOf creates a new instance of integer. Parse line.
      data.add(Integer.valueOf(line));
    }
    //Get data what is in Array. Will create an array automatically.
    //to Array is a method call but create data sized array
    return data.toArray(new Integer[data.size()]);
    //Catch if line has bad data for Num OR IO exception.
  } catch (NumberFormatException | IOException ex) {
    // key is in property
    System.out.printf(resources.getString(READ_ERROR_MESSAGE_KEY));
    throw ex;
  }
}

//Define the array label as x
/*private class Binarysearch (int needle, Integer[] haystack()) {
  int itterate, t, x;
  t== itterate
      while (x[i] != t) {
        if (x[i] > t)
          //i -= n/(2^(j+1))
          j++;
         else if x[i] < t
       //i -= n/(2^(j+1))
         j++;
         else
           break;
        return x[i];
      }
               
}*/


//Real search

private static int findValue(int needle, Integer[] haystack) {
  //start at 0 until the length
  return findValue(needle, haystack, 0, haystack.length);
}

private static int findValue(int needle, Integer[] haystack, int start, int end) {
  if (end <= start) {
    //return negative start plus 1; Bitflow tilda ~ flip all bits
    return ~start;
  }
//average to get midpoint; haystack is array of objects; auto unboxing
  int midpoint = (start + end) / 2;
  int test = haystack[midpoint];
  if (test == needle) {
    return midpoint;
  }
  if (test < needle) {
    
    return findValue(needle, haystack, midpoint + 1, end);
  }
  //test is greater than needle; lets search from start to midpoint
  return findValue(needle, haystack, start, midpoint);
}

}
    
    
    
    


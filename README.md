# Simple-Project-FileJson
File Json

![Image description](https://i.ytimg.com/vi/gnj-Df7QQHU/maxresdefault.jpg)

JSON stands for JavaScript Object Notation.It is an independent data exchange format and is the best alternative for XML. This chapter explains how to parse the JSON file and extract necessary information from it.

Android provides four different classes to manipulate JSON data. These classes are JSONArray,JSONObject,JSONStringer and JSONTokenizer.

The first step is to identify the fields in the JSON data in which you are interested in. For example. In the JSON given below we interested in getting temperature only.

{

   "sys":
   
   {
   
      "country":"GB",
      
      "sunrise":1381107633,
      
      "sunset":1381149604
      
   },
   
   "weather":[
   
      {
      
         "id":711,
         
         "main":"Smoke",
         
         "description":"smoke",
         
         "icon":"50n"
         
      }
      
   ],
	
  "main":
  
   {
   
      "temp":304.15,
      
      "pressure":1009,
      
   }

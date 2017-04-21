PSEUDOCODE:

1.For every product in the input we traverse the whole file and check for that product in product column.
2.Then we save that shop_id with product_price in a hashmap "productshopMap" and mark the visit of that shop in a hashmap "VisitedMap" for the first time.
3.For the products coming after first one we check for that product in product column and save the shop_id with product_price in a hashmap "productshopMap" and 
  at the same time if the shop has been visited for every previous product then we move it to a common hashmap which will contain the sum of price of every product
  in every shop.
4.Lastly when we have done the above process for every product in input then we traverse the common map and print the shop_id with minimum sum of price.

PRE_REQUISITES:

*Since we get entries from a CSV file with ',' as a delimiter that is why I have entered product names(third column) in file as space seperated rather than comma
 seperated. If we want product names in CSV file to be be comma(,) seperated then we will have to make changes in code as to make it space seperated in code.
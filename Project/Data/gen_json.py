import json
import sys
# read file
f_name = str(sys.argv[1])
products = []
five_star = []
one_star = []
img_url = []
with open(f_name+'_proc.json') as json_file:
    data = json.load(json_file)
    for p in data[f_name]:
        products.append(p['product'])
        five_star.append(p['fiveStar'])
        one_star.append(p['oneStar'])
        img_url.append(p['img_url'])

#print(products)
#print(five_star)
#print(one_star)
#print(img_url)

product_title = []
with open(f_name+'_title.json') as json_file_t:
    jsonObject = json.load(json_file_t)
    for line in jsonObject:
        for key, value in line.items():
            product_title.append(value)
            
#print(product_title)

data = {}
print("[")
for x in range(10):
  if x != 9:
      print("{\"title\": \"" + product_title[x] + "\" ,\"product\": \"" + products[x] + "\" , \"fiveStar\" : " + str(five_star[x]) + " , \"oneStar\" : " + str(
                one_star[x]) + " , \"img_url\" : \"" + img_url[x] + "\" },")
  else : 
        print("{\"title\": \"" + product_title[x] + "\" ,\"product\": \"" + products[x] + "\" , \"fiveStar\" : " + str(five_star[x]) + " , \"oneStar\" : " + str(
                one_star[x]) + " , \"img_url\" : \"" + img_url[x] + "\" }")
print("]")
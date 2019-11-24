import timeit
import numpy as np
from collections import OrderedDict
from operator import itemgetter
import json

start = timeit.default_timer()
productMap = {}
fiveStar = {}
oneStar = {}
f_name = "music"
file = open("Data/"+f_name+".json", "r", encoding="utf8")


items = []
for x in file:
    #a = x.split('\t')
    line_dict = json.loads(x)
    # a[3] == Pid
    items.append(line_dict['asin'])
    # a[5] == Pname a[7] == rating
    #productMap[a[3]] = a[5]
    #if a[7] == 'star_rating': continue
    if int(line_dict['overall']) >= 3:
        if (line_dict['asin'] in fiveStar):
            fiveStar[line_dict['asin']] += 1
        else:
            fiveStar[line_dict['asin']] = 1

    else:
        if (line_dict['asin'] in oneStar):
            oneStar[line_dict['asin']] += 1
        else:
            oneStar[line_dict['asin']] = 1

items_unq = np.array(items)
#print("Unique products in the file are :",np.unique(items_unq).size)

freq = {}
for item in items:
    if (item in freq):
        freq[item] += 1
    else:
        freq[item] = 1

freq_s = OrderedDict(sorted(freq.items(), key=itemgetter(1), reverse=True))

count = 0
json = []
products = []
fs = []
os = []
img =[]

print("{ \""+f_name+ "\" : [")

for key, value in freq_s.items():

    # print("% s : % d" % (key, value))
    #print("%s : %s" % (key, productMap[key]))
    #print(key)
    product_url = "http://www.amazon.com/dp/"+key
    products.append(product_url)
    img_url = "http://images.amazon.com/images/P/"+key+".01._PE30_PI_SCLZZZZZZZ_.jpg"
    img.append(img_url)

    fs.append(fiveStar[key])
    os.append(oneStar[key])
    #print("5 stars: ", fiveStar[key])
    #print("1 stars: ", oneStar[key])


    count += 1
    if count != 10:
        print(
            "{\"product\": \"" + product_url + "\" , \"fiveStar\" : " + str(fiveStar[key]) + " , \"oneStar\" : " + str(
                oneStar[key]) + " , \"img_url\" : \"" + img_url + "\" },")
    elif count == 10 :
        print(
            "{\"product\": \"" + product_url + "\" , \"fiveStar\" : " + str(fiveStar[key]) + " , \"oneStar\" : " + str(
                oneStar[key]) + " , \"img_url\" : \"" + img_url + "\" }")
        break


#print("Products =",products)
#print("FiveStars =",fs)
#print("OneStar =",os)
#print("Img =",img)
print ("]}")
stop = timeit.default_timer()
#print('Time: ', stop - start)

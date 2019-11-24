import nltk
from nltk import FreqDist
#nltk.download('stopwords')
import pandas as pd
pd.set_option("display.max_colwidth", 200)
import numpy as np
import json
import re
import gzip
import spacy
import sys

import gensim
from gensim import corpora
from collections import OrderedDict
from operator import itemgetter

import pyLDAvis
import pyLDAvis.gensim
import matplotlib.pyplot as plt
import seaborn as sns
from nltk.corpus import stopwords

f_name = str(sys.argv[1])
stop_words = stopwords.words('english')
file = open(f_name+".json", "r", encoding="utf8")

items = []
reviews_dict=dict()

for x in file:
    line_dict = json.loads(x)
    
    key = line_dict['asin']
    #print(key)
    if key not in reviews_dict:
        reviews_dict[key] = []
    
    
    value=line_dict['reviewText']
    reviews_dict[key].append(value)
    
    items.append(line_dict['asin'])

freq = {}
for item in items:
    if (item in freq):
        freq[item] += 1
    else:
        freq[item] = 1

freq_s = OrderedDict(sorted(freq.items(), key=itemgetter(1), reverse=True))

review_text=[]
for key,value in freq_s.items():
    review_text = reviews_dict[key]
    top = key
    break
	
def freq_words(x, terms = 30):
    all_words = ' '.join([text for text in x])
    all_words = all_words.split()
    fdist = FreqDist(all_words)
    words_df = pd.DataFrame({'word':list(fdist.keys()), 'count':list(fdist.values())})
    # selecting top 20 most frequent words
    d = words_df.nlargest(columns="count", n = terms)
    plt.figure(figsize=(20,5))
    ax = sns.barplot(data=d, x= "word", y = "count")
    ax.set(ylabel = 'Count')
    plt.show()
	
ser=pd.Series(review_text)

# function to remove stopwords
def remove_stopwords(rev):
  rev_new = " ".join([i for i in rev if i not in stop_words])
  return rev_new
# remove short words (length < 3)
ser = ser.apply(lambda x: ' '.join([w for w in x.split() if len(w)>2]))
# remove stopwords from the text
reviews = [remove_stopwords(r.split()) for r in ser]
# make entire text lowercase
reviews = [r.lower() for r in reviews]

nlp = spacy.load('en', disable=['parser', 'ner'])
def lemmatization(texts, tags=['NOUN', 'ADJ']): # filter noun and adjective
     output = []
     for sent in texts:
           doc = nlp(" ".join(sent))
           output.append([token.lemma_ for token in doc if token.pos_ in tags])
     return output

tokenized_reviews = pd.Series(reviews).apply(lambda x: x.split())
#print(tokenized_reviews[0])

reviews_2 = lemmatization(tokenized_reviews)
#print(reviews_2[0]) # print lemmatized review

reviews_3 = []
for i in range(len(reviews_2)):
  reviews_3.append(' '.join(reviews_2[i]))
ser = reviews_3
#freq_words(ser,10)

all_words = ' '.join([text for text in ser])
all_words = all_words.split()
fdist = FreqDist(all_words)
words_df = pd.DataFrame({'word':list(fdist.keys()), 'count':list(fdist.values())})
# selecting top 20 most frequent words
d = words_df.nlargest(columns="count", n = 10)
print("{\"trend\": \"",d['word'].iloc[0],"\",")
print("\"product\":","\"http://www.amazon.com/dp/"+top+"\",")
print("\"img\":","\"http://images.amazon.com/images/P/"+top+".01._PE30_PI_SCLZZZZZZZ_.jpg\"}")

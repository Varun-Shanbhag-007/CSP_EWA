# CSP_EWA
EWA Project Digital Marketing and Trends Readme


Pre-Requisite:
#JDK 8
#Tomcat 7 
#Python 3 (Libraries used/required json,scrapy,numpy,collections,operator,surprise,os,csv,nltk,pandas,gzip,spacy,re,gensim,pyLDAvis,matplotlib,seaborn,operator)


1. Download reviews data from http://jmcauley.ucsd.edu/data/amazon/ pick any category we have picked 4 for the project viz : Books, Sports, Video Game and Digital music
2. Copy downloaded json in Webapps/Project/Data folder as <category_name>.json
3. Run following script to generate top products gen_json.py <category_name> Refer sshot
4. Run following script to generate top trends trends_gen.py <category_name> Refer sshot
5. Import all *.sql files on to mysql-db placed in data/data_dumps folder using mysql workbench feature
6. Import Mongo DB Collection using Mongo Compass under DB (CustomerReviews) Collection (ProductsVisited) using Mongo Compass placed data/data_dumps
7. Place Project folder in webapps directory of tomcat
8. Launch Mysql followed by mongo
9. Launch Tomcat
10.Ready to go
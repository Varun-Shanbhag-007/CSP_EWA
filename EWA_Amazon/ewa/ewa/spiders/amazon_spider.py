# -*- coding: utf-8 -*-
import scrapy
from ..items import EwaItem
from scrapy.http.request import Request

class AmazonSpiderSpider(scrapy.Spider):
    name = 'amazon_s'
    allowed_domains = ['amazon.com']
    start_urls = ['http://www.amazon.com/dp/B001HBHNHE']
    other_urls = ['http://www.amazon.com/dp/B001T7QJ9O', 'http://www.amazon.com/dp/B000S5ODN2', 'http://www.amazon.com/dp/B0010O748Q', 'http://www.amazon.com/dp/B0000C50K3', 'http://www.amazon.com/dp/B002ZYRV2E', 'http://www.amazon.com/dp/B002OKWHVO', 'http://www.amazon.com/dp/B000GCRWCG', 'http://www.amazon.com/dp/B001HBHNHY', 'http://www.amazon.com/dp/B0035L35A8']

    def parse(self, response):
        items = EwaItem()
        title = response.xpath('//span[@id="productTitle"]/text()').extract()
        #title = response.xpath('//h1[@id="title"]/span/text()').extract()
        items['product_name'] = ''.join(title).strip()
        yield items
        for url in AmazonSpiderSpider.other_urls:
            yield response.follow(url,callback=self.parse)

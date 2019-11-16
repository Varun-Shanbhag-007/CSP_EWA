# -*- coding: utf-8 -*-
import scrapy
from ..items import EwaItem

class AmazonSpiderSpider(scrapy.Spider):
    name = 'amazon_s'
    allowed_domains = ['amazon.com']
    start_urls = ['http://www.amazon.com/dp/030758836X', 'http://www.amazon.com/dp/0439023483', 'http://www.amazon.com/dp/0375831002', 'http://www.amazon.com/dp/038536315X', 'http://www.amazon.com/dp/0439023513', 'http://www.amazon.com/dp/0316055433', 'http://www.amazon.com/dp/0385537859', 'http://www.amazon.com/dp/0007444117', 'http://www.amazon.com/dp/147674355X', 'http://www.amazon.com/dp/0399159347']

    def parse(self, response):
        items = EwaItem()
        title = response.xpath('//span[@id="productTitle"]/text()').extract()
        #title = response.xpath('//h1[@id="title"]/span/text()').extract()
        items['product_name'] = ''.join(title).strip()


        yield items

#!/bin/bash
curl -k -v "https://smarty.mail.ru/api/v1/objects/detect?oauth_provider=mcs&oauth_token=2KaVNdTG7CAzeLMGgEEMNnVCF7BfFjYwK3afDziNBWcUWAAvyJ" -F file_0=@test.jpg  -F meta='{"mode":["object"],"images":[{"name":"file_0"}]}'

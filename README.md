**Made with** ![java](https://user-images.githubusercontent.com/32490159/138323715-ec77740d-0eb1-42ad-9a38-17b0a4061a5b.jpeg)
![gradle](https://user-images.githubusercontent.com/32490159/138323739-7b09ca9d-f8fe-4b5a-9494-9858acf957e1.png)
![junit](https://user-images.githubusercontent.com/32490159/138323764-ed4401f6-2c32-404f-af38-8bf6c040cc33.png)
![jenkins](https://user-images.githubusercontent.com/32490159/138324083-1992937f-946f-4b1d-9fe3-dbe3155ac0d0.jpeg)
![allure](https://user-images.githubusercontent.com/32490159/138323809-440254e7-c44f-4129-a2d1-dcdfde4eb1fa.jpeg) 
![Telegram-logo](https://user-images.githubusercontent.com/32490159/138324199-27e1eea4-b750-4b78-a777-0eaab144824e.png)

# API tests for reqres.in

<a target="_blank" href="https://reqres.in/">reqres.in</a> is a hosted REST API which provides a few handlers.
These are the tests which check these handlers' functionality.

# Allure TestOps

Click <a target="_blank" href="https://allure.autotests.cloud/project/644/dashboards">here</a> to see a list of automated test cases.

Here are the test cases:


And this is the test launch overview - all 7 tests passed, yay 😺

# Run tests 

## Jenkins job

Jenkins is a tool which lets you run the tests from. 
Click <a target="_blank" href="https://jenkins.autotests.cloud/job/07-oecowgirl-reqresrest">here</a> to do that.

## Run locally

If you've downloaded these tests to your computer, run them with the following command:
```bash
gradle clean test
```

# Telegram notifications

Notifications about test results are sent to Telegram:
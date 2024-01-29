# Orders-and-Notifications-Module-Web-API
## Overview

The Orders and Notifications API Module is a Java Spring Boot project designed to seamlessly manage online purchase orders and associated notifications within a larger software system. This module focuses on providing a user-friendly interface through a RESTful API, allowing easy integration and efficient handling of orders, notifications, and user accounts.

## Features

### 1. RESTful API

The module exposes a user-friendly RESTful API, simplifying the integration process and enabling users to interact with the system effortlessly. The API offers a set of well-defined endpoints for managing orders, notifications, and user accounts.

### 2. Dynamic Notification System

The module incorporates a dynamic notification system that enhances user communication. Key features of the notification system include:

- **Template Support:**
  - Users can employ customizable notification templates, allowing for consistent and personalized communication.

- **Multilingual Support:**
  - The system supports notifications in multiple languages, ensuring effective communication with a diverse user base.

- **Communication Channels:**
  - Notifications can be delivered through various channels, including email and SMS, providing flexibility in user engagement.

### 3. Orders System

The orders system streamlines the process of managing purchase orders, focusing on user convenience and flexibility. Notable features of the orders system include:

- **Intuitive Order Creation:**
  - Users can easily create new orders, specifying products, quantities, and any additional details required.

- **Order Modification:**
  - The system allows users to modify existing orders, accommodating changes in product selection or quantities.

- **Order Tracking:**
  - Users can track the status and details of their orders, providing transparency throughout the entire purchase process.

### 4. Real-Time Product Display

The system provides real-time product information, displaying a comprehensive list of available products. Users can view remaining product counts per category, ensuring up-to-date insights into the available inventory.

### 5. User Account Management

The module includes user account management functionalities, allowing users to:

- **Register Accounts:**
  - Users can register accounts with the system, providing a personalized experience for future transactions.

## API Endpoints

Explore the various API endpoints to interact with the system. Each endpoint is designed to provide specific functionalities related to orders, notifications, and user accounts.
|  | Request     Type | Http request | Request Body | Requirements |
| --- | --- | --- | --- | --- |
| Register | Post | /accounts/register | Check Usage Scenario [Register]  | A customer should be able to create an account and put a specific balance using that account. |
| Login | Get | /accounts/login | Check Usage Scenario [Login] | User Should be able to Login. |
| Products | Get | /products/all |  | A list of all the products currently available for purchase should be returned. |
| Category Items | Get | /products/categories/get_products_count/{CategoryType} |  | a count of the remaining parts from each category should be available. |
| Place Simple order  | Post | /orders/place | Check Usage Scenario [Place Simple order] | a customer can place a simple order, where such an order would include a single product or several products. |
| Place Compound order | Post | /orders/place | Check Usage Scenario [Place Compound Order] | A customer can make a compound order, where that order can include various orders headed to near-by locations, in addition to his own products, to lessen the shipping fees. |
| Order Info | Get | /orders/get/1{orderID}   |  | You should be able to list all the details of both simple and compound orders. |
| Ship Order | PUT | /orders/get/1{orderID}   |  | After placing the order, the user can ship the order. |
| Cancel Order | Delete | /orders/cancel/1{orderID} |  | Customers can cancel an order placement. |
| Cancel Shipping | Put | /orders/cancel_shipping/1{orderID} |  | Customers can cancel its shipping within a pre-configured automated duration. |
| Notifications | Get | /notifications/get_in_queue |  | For created notifications, you should implement a "notifications Queue", where you insert "notifications" that ARE TO BE SENT. |
| Notifications Statistics | Get | /notifications/statistics/get_most_notified_mail |  | The system should provide live statistics on the most notified email-address/phone-number. |
| Notifications Statistics | Get | /notifications/statistics/get_most_notified_phone |  | The system should provide live statistics on the most notified email-address/phone-number. |
| Notifications Statistics | Get | /notifications/statistics/get_most_notified_mail_or_phone |  | The system should provide live statistics on the most frequently used communication channel. |
| Notifications Statistics | Get | /notifications/statistics/get_most_notified_template |  | The system should provide live statistics on the most frequently used notification template. |

# COMPUTER DEVICE SHOP #
An application that helps to organize a workflow of a computer device shop. It provides tools for employees to manage the
site (e.g. add and change all data models, track client orders and notify users about progress on those orders)
and for clients to use it (e.g. receiving emails on orders progress, managing
their profiles and more).

Application Role | Description
--- | ---
Guest | Unauthenticated user
Not confirmed user | Authenticated user without confirmed email that has reduced access rights
Client | Authenticated user
Manager | Authenticated user
Admin | Authenticated user with it's own exclusive rights and an access to pages dedicated to any other role

### Guest ###
* Has access to home page
* Can register on site and receiving confirmation link for registered profile via email
* Login on site and using forgot password function which helps to restore password to an account via registered email
* Change application language

### Not confirmed user ###
* Has access to home and profile pages
* Can use existing confirmation link to finish registration and acquire full assigned role access
* View and change profile info:
    * Change email
    * Change login
    * Change password
* Resend and receive confirmation link to current email specified in user profile
* Change application language


### Client ###
* Has access to home, profile, card, device, my orders and product pages
* Can view and change profile info:
    * Change email
    * Change login
    * Change password
*Add devices to card and order a they:
    * Search device of type, price, description, name and id,  for ease of use;
    * Creating an order;
* View ordered devices:
    * Search through your orders by id order or status
    * Tracking status of your order (ordered, in progress, finished)
* Receiving a notification via email about changes of order status
* Change application language

### Manager ###
* Has access to home, profile, and orders pages
* Can view and change profile info:
    * Change email
    * Change login
    * Change password
* View and interact with device table:
    * View devices
    * Add device
    * Update device
    * Delete device
    * Filter by criteria: id, name, price, description, type
* View and interact with orders table:
    * Viewing existing active orders (with ordered and in progress statuses)
    * Filtering orders by criteria: user name, id order, order status
    * Changing "ordered" order status to "in progress" and sending an auto email notification to the client
* Change application language

### Admin ###
* Has access to all **Client** and **Manager** pages as well as an admin panel
* View and interact with admin panel:
    * Users
        * View users
        * Add user
        * Update user
        * Delete user and all associated orders
        * Filter by criteria: id, email, login, role, status
* Change application language

### Database EER Diagram ###
![Database scheme](https://github.com/Philip287/store/blob/master/schema/schema.png)
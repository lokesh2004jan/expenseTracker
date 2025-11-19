# Expense Tracker – Android (Jetpack Compose) + Spring Boot + MongoDB

This project is a full-stack **Expense Tracker** built with:

- **Android (Jetpack Compose)** – Frontend  
- **Spring Boot (Java)** – Backend  
- **MongoDB Atlas** – Database  

The app supports adding, editing, deleting, viewing, and summarizing expenses by category.

---

# 🚀 Features

### 📱 **Android App (Frontend)**
- Jetpack Compose UI  
- Add expense (amount, description, date, category)  
- Edit & Delete expenses  
- Fetch all expenses by user ID  
- Dashboard with total spend & category-wise summary  
- Retrofit + OkHttp for API calls  
- Responsive UI (WindowSizeClass)  
- Material 3 design  

### 🖥 **Backend (Spring Boot)**
- RESTful APIs  
- MongoDB Atlas integration  
- Endpoints:
  - `POST /expenses` – Add expense  
  - `PUT /expenses/{id}` – Update expense  
  - `DELETE /expenses/{id}` – Delete expense  
  - `GET /expenses` – List/filter expenses  
- Input validation  
- Service + Repository layers  

---

# 🗄 **Backend (Spring Boot) – How to Run**

1. Unzip the **backend** folder.
2. Open a terminal inside the backend directory.
3. Run the backend using:


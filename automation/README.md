# Saucedemo QA Automation

This folder contains Selenium + TestNG automation scripts written in **Java** to validate core functionality of the [Saucedemo](https://www.saucedemo.com/) application.

The tests here complement the manual test cases I documented in the spreadsheet (TC1–TC18) and aim to cover positive, negative, and edge-case scenarios.

---

## Structure

- `LoginTest.java` → Verifies login functionality with valid, invalid, and empty credentials
- `CartTest.java` → Validates cart behavior: adding, removing, and handling multiple products
- `CheckoutTest.java` → Covers checkout flow including positive checkout, negative checkout, and known bug scenarios

---

## Test Coverage

### Login Tests (from `LoginTest.java`)
- Successful login with valid credentials (TC1)
- Login with empty username and password (TC5)
- Login with locked-out user (negative case, TC2/TC3 variation)

### Cart Tests (from `CartTest.java`)
- Add single product to cart (TC9)
- Add multiple products to cart (TC11)
- Remove product from cart (TC14)

### Checkout Tests (from `CheckoutTest.java`)
- Empty cart should not allow checkout (TC17 – currently **bug exists**)
- Positive checkout with valid user details (TC23)
- Negative checkout with missing required fields (negative case, TC19/TC20 variation)

---

## Tools & Technologies

- **Language**: Java
- **Frameworks**: Selenium WebDriver, TestNG
- **Build Tool**: Maven
- **Browser**: Chrome (via ChromeDriver)

---

## How to Run

1. Clone the repo:
   git clone https://github.com/<your-username>/saucedemo-qa.git
   cd saucedemo-qa/automation
2. Install dependencies:
    mvn clean install
3. Run the tests:
   mvn test




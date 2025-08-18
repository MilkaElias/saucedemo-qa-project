# Bug Reports – SauceDemo QA Project

## Summary Table
| Issue Type | Key      | Summary                                                   | Reporter      |
|------------|----------|-----------------------------------------------------------|---------------|
| Bug        | BUG-005  | Checkout succeeds with invalid address input              | Milka Worku   |
| Bug        | BUG-004  | Product selection bar missing on cart page during checkout | Milka Worku   |
| Bug        | BUG-003  | Checkout working while cart is empty                      | Milka Worku   |
| Bug        | BUG-002  | Cart items persist after logout and login with another user | Milka Worku |
| Bug        | BUG-001  | Unable to add same product twice; Add to Cart disappears   | Milka Worku   |

---

## Detailed Reports

### BUG-005 – Checkout succeeds with invalid address input
**Steps to Reproduce**  
1. Log in with valid credentials  
2. Add product(s) to cart  
3. Go to checkout  
4. Enter invalid address (e.g., `abcde`)  
5. Submit  

**Expected Result**  
System should display an error message like *"Please input a valid address to proceed with checkout."*  

**Actual Result**  
Checkout succeeds and continues to the next step.  

**Status**: Fail  
**Severity**: High    

---

### BUG-004 – Checkout working while cart is empty
**Steps to Reproduce**  
1. Log in with valid credentials  
2. Ensure the cart is empty
3. Press Checkout  

**Expected Result**  
User should see an error message unable to check out when cart is empty.  

**Actual Result**  
Checkout is successful, and user is redirected to the address page.  

**Status**: Fail  
**Severity**: Medium

---

### BUG-003 – Product selection bar missing on cart page during checkout
**Steps to Reproduce**
1. Log in with valid credentials
2. Add multiple products to cart
3. Go to checkout page

**Expected Result**
User should see a selection bar to choose specific products for checkout.

**Actual Result**
No product selection bar is available.

**Status**: Fail
**Severity**: Medium

---

### BUG-002 – Cart items persist after login with a different account
**Steps to Reproduce**
1. Log in with valid credentials
2. Add multiple products to cart
3. Log out of the account
4. Log in with a different valid credentials
5. Access the cart

**Expected Result**
Cart items should reset.

**Actual Result**
Previous account's cart items persisted and are available in the new account's cart.

**Status**: Fail
**Severity**: Medium

---

### BUG-001 – Unable to add same product twice; Add to cart button disappears after first add
**Steps to Reproduce**
1. Log in with valid credentials
2. Add a product to cart
3. Try adding the same product to the cart

**Expected Result**
Add to cart button should remain visible, allowing the same product to be added again.

**Actual Result**
Add to cart button disappears after the first add, making it impossible to add the same item to the cart.

**Status**: Fail
**Severity**: Medium

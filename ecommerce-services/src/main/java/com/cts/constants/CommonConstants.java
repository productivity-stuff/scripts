package com.cts.constants;

public interface CommonConstants {

	public interface Cart {
		String ADD_TO_CART_SUCCESS = "Cart Details Saved Successfully!";
		String ADD_TO_CART_FAILED = "Oops! Add to cart failed, please try after sometime";
		String GET_TO_CART_SUCCESS = "Items fetched successfully";
		String GET_TO_CART_FAILED = "OOPS ! Items are not fetched successfully";
		String CART_LIST_FOUND = "CartList found successfully!";
		String CART_LIST_NOT_FOUND = "CartList not found successfully!";
		String CART_FAILED = "Oops! unable to process the cart flow ,please try after sometime";
		String CART_DELETED = "Cart deleted successfully";
		String UNABLE_TO_DELETE_CART = "Oops! unable to delete cart";
		String CART_NOT_FOUND = "Oops! cart not found";
	}

	public interface Book {
		String SAVED_THE_BOOK_SUCCESS = "Book details saved successfully!";
		String SAVED_THE_BOOK_FAILED = "Oops! the books are not saved,please try after sometime";
		String BOOK_FAILED = "Oops! Unable to process book flow, please try after sometime";
		String BOOK_NOT_FOUND = "Oops! books not found!";
		String BOOK_FOUND = "Book Data Found!";
		String BOOK_LIST_FOUND = "BookList found successfully!";
		String BOOK_LIST_NOT_FOUND = "Oops! BookList not found successfully!";
		String BOOK_DELETED = "Book deleted successfully";
		String UNABLE_TO_DELETE_BOOK = "Oops! unable to delete book";

	}

	public interface OrderDetails {
		String SAVED_THE_ORDERDETAILS_SUCCESS = "Order details saved successfully!";
		String SAVED_THE_ORDERDETAILS_FAILED = "Oops! the orderDetails not saved successfully,please try after sometime";
		String GET_TO_ORDER_SUCCESS = "Orders fetched successfully!";
		String GET_TO_ORDER_FAILED = "Oops! orders not fetched successfully";
		String ORDER_LIST_FOUND = "OrderList found successfully!";
		String ORDER_LIST_NOT_FOUND = "Oops! OrderList not found successfully!";
		String ORDER_FAILED = "Oops! Unable to process order flow, please try after sometime";
		String ORDER_DELETED = "Order deleted successfully";
		String UNABLE_TO_DELETE_ORDER = "Oops! unable to delete order";
		String ORDER_NOT_FOUND = "Oops! order not found!";


	}

	public interface UserDetails {
		String SAVED_THE_USERDETAILS_SUCCESS = "User details saved successfully!";
		String SAVED_THE_USERDETAILS_FAILED = "Oops! the userDetails not saved successfully,please try again sometime";
		String GET_TO_USER_SUCCESS = "Users fetched successfully!";
		String GET_TO_USER_FAILED = "Oops! users not fetched successfully!";
		String USER_LIST_FOUND = "UserList found successfully!";
		String USER_LIST_NOT_FOUND = "Oops! UserList not found successfully!";
		String USER_FAILED = "Oops! Unable to process user flow, please try after sometime";
		String USER_DELETED = "user deleted successfully";
		String UNABLE_TO_DELETE_USER = "Oops! unable to delete user";
		String USER_NOT_FOUND = "Oops! user not found!";

	}
}

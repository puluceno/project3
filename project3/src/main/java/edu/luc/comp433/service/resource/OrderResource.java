package edu.luc.comp433.service.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.luc.comp433.model.Order;
import edu.luc.comp433.service.OrderService;
import edu.luc.comp433.service.workflow.OrderActivity;

/**
 *
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@Path("/orders")
public class OrderResource implements OrderService {

	private OrderActivity orderActivity = new OrderActivity();

	@Override
	@POST
	@Consumes("{application/json, application/xml}")
	public Response createOrder(Order order) {
		return orderActivity.createOrder(order);
	}

	@Override
	@PUT
	@Path("/{orderId:[0-9][0-9]*}")
	public Boolean cancelOrder(@PathParam("orderId") Short orderId) {
		return orderActivity.cancelOrder(orderId);
	}

	@Override
	@GET
	@Path("/{orderId:[0-9][0-9]*}")
	@Produces("{application/json,application/xml}")
	public Response checkOrderStatus(@PathParam("orderId") Short orderId) {
		return orderActivity.checkOrderStatus(orderId);
	}

	@Override
	@GET
	@Path("/{login}")
	@Produces("{application/json,application/xml}")
	public List<Order> findOrderByCustomerLogin(@PathParam("login") String login) {
		return orderActivity.findOrderByCustomerLogin(login);
	}

}

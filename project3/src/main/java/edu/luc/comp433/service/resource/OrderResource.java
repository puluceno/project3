package edu.luc.comp433.service.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.model.Order;
import edu.luc.comp433.model.enumerator.OrderStatus;
import edu.luc.comp433.service.OrderService;
import edu.luc.comp433.service.exception.OrderNotFoundException;
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
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Response createOrder(Order order) {
		Response response = null;
		try {
			if (null != order) {
				order = orderActivity.createOrder(order);
				response = Response
						.created(URI.create("/orders/" + order.getId()))
						.entity(order).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@Override
	@PUT
	@Path("/{orderId:[0-9]+}/cancel")
	@Produces({ "application/json", "application/xml" })
	public Response cancelOrder(@PathParam("orderId") Short orderId) {
		Response response = null;
		try {
			if (null != orderId) {
				boolean orderCancelled = orderActivity.cancelOrder(orderId);
				response = Response.ok().entity(orderCancelled).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@Override
	@GET
	@Path("/{orderId:[0-9]+}/status")
	@Produces({ "application/json", "application/xml" })
	public Response checkOrderStatus(@PathParam("orderId") Short orderId) {
		Response response = null;
		try {
			if (null != orderId) {
				OrderStatus status = orderActivity.checkOrderStatus(orderId);
				response = Response.status(Status.OK).entity(status).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		}
		return response;
	}

	@Override
	@GET
	@Path("/{login}")
	@Produces({ "application/json", "application/xml" })
	public Response findOrderByCustomerLogin(@PathParam("login") String login) {
		Response response = null;
		List<Order> orders = null;
		try {
			orders = orderActivity.findOrderByCustomerLogin(login);
			response = Response.ok().entity(orders).build();
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		}
		return response;
	}

}

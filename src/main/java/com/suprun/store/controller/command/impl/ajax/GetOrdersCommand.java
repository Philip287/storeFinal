package com.suprun.store.controller.command.impl.ajax;

import com.suprun.store.entity.Order;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.OrderService;
import com.suprun.store.service.criteria.OrderFilterCriteria;
import com.suprun.store.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.suprun.store.controller.command.RequestAttribute.*;

public class GetOrdersCommand extends AbstractAjaxCommand<Order> {

    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    Pair<Long, List<Order>> filterForDatatables(int start, int length, String filterCriteria, String searchValue) throws ServiceException {
        return StringUtils.isEmpty(searchValue)
                ? orderService.filter(start, length, OrderFilterCriteria.NONE, null)
                : orderService.filter(start, length, OrderFilterCriteria.valueOf(filterCriteria), searchValue);
    }

    @Override
    Pair<Long, List<Order>> filterForSelect(int start, int pageSize, String filterCriteria, String searchValue) throws ServiceException {
        return orderService.filter(start, pageSize, OrderFilterCriteria.valueOf(filterCriteria), searchValue);
    }

    @Override
    Optional getFromServiceById(long entityId) throws ServiceException {
        return orderService.findById(entityId);
    }

    @Override
    protected void processSelectRequest(HttpServletRequest request, Map<String, Object> responseMap) throws ServiceException {
        String searchValue = request.getParameter(TERM);
        String userId = String.valueOf(request.getSession().getAttribute(ID_USER));
        int page = Integer.parseInt(request.getParameter(PAGE));
        int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE));
        int start = pageSize * (page - 1);

        Pair<Long, List<Order>> pair = filterForSelect(start, pageSize, userId, searchValue);
        long recordsFetched = pair.getLeft();
        List<Order> orders = pair.getRight();
        responseMap.put(RESULTS, orders);
        responseMap.put(PAGINATION_MORE, (long) page * pageSize < recordsFetched);

    }

    @Override
    protected void processDatatablesRequest(HttpServletRequest request, Map<String, Object> responseMap) throws ServiceException {
        int start = Integer.parseInt(request.getParameter(PAGINATION_START));
        int length = Integer.parseInt(request.getParameter(PAGINATION_LENGTH));
        int draw = Integer.parseInt(request.getParameter(DRAW));
        String filterCriteria = request.getParameter(FILTER_CRITERIA);
        String searchValue = request.getParameter(SEARCH_VALUE);

        Pair<Long, List<Order>> pair = filterForDatatables(start, length, filterCriteria, searchValue);


        long recordsFetched = pair.getLeft();
        List<Order> orders = pair.getRight();

        responseMap.put(DRAW, draw);
        responseMap.put(RECORDS_TOTAL, recordsFetched);
        responseMap.put(RECORDS_FILTERED, recordsFetched);
        responseMap.put(DATA, orders);
    }

}

package com.suprun.store.controller.command.impl.ajax;

import com.suprun.store.entity.DeviceHasOrder;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceHasOrderService;
import com.suprun.store.service.criteria.DeviceHasOrderFilterCriteria;
import com.suprun.store.service.impl.DeviceHasOrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.suprun.store.controller.command.RequestAttribute.*;
import static com.suprun.store.controller.command.RequestAttribute.DATA;

public class GetDevicesHasOrdersCommand extends AbstractAjaxCommand<DeviceHasOrder> {

    private final DeviceHasOrderService deviceHasOrderService = DeviceHasOrderServiceImpl.getInstance();

    @Override
    Pair<Long, List<DeviceHasOrder>> filterForDatatables(int start, int length, String filterCriteria, String searchValue) throws ServiceException {

        return StringUtils.isEmpty(searchValue)
                ? deviceHasOrderService.filter(start, length, DeviceHasOrderFilterCriteria.NONE, null)
                : deviceHasOrderService.filter(start, length, DeviceHasOrderFilterCriteria.valueOf(filterCriteria), searchValue);
    }

    @Override
    Pair<Long, List<DeviceHasOrder>> filterForSelect(int start, int pageSize, String filterCriteria, String searchValue) throws ServiceException {

        return deviceHasOrderService.filter(start, pageSize, DeviceHasOrderFilterCriteria.valueOf(filterCriteria), searchValue);
    }

    @Override
    Optional<DeviceHasOrder> getFromServiceById(long entityId) throws ServiceException {
        return deviceHasOrderService.findById(entityId);
    }

    @Override
    protected void processSelectRequest(HttpServletRequest request, Map<String, Object> responseMap) throws ServiceException {
        String searchValue = request.getParameter(TERM);
        String userId = String.valueOf(request.getSession().getAttribute(ID_USER));
        int page = Integer.parseInt(request.getParameter(PAGE));
        int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE));
        int start = pageSize * (page - 1);

        Pair<Long, List<DeviceHasOrder>> pair = filterForSelect(start, pageSize, userId, searchValue);
        long recordsFetched = pair.getLeft();
        List<DeviceHasOrder> deviceHasOrder = pair.getRight();
        responseMap.put(RESULTS, deviceHasOrder);
        responseMap.put(PAGINATION_MORE, (long) page * pageSize < recordsFetched);

    }

    @Override
    protected void processDatatablesRequest(HttpServletRequest request, Map<String, Object> responseMap) throws ServiceException {
        int start = Integer.parseInt(request.getParameter(PAGINATION_START));
        int length = Integer.parseInt(request.getParameter(PAGINATION_LENGTH));
        int draw = Integer.parseInt(request.getParameter(DRAW));
        String filterCriteria = request.getParameter(FILTER_CRITERIA);
        String searchValue = request.getParameter(SEARCH_VALUE);

        Pair<Long, List<DeviceHasOrder>> pair = filterForDatatables(start, length, filterCriteria, searchValue);

        long recordsFetched = pair.getLeft();
        List<DeviceHasOrder> orders = pair.getRight();

        responseMap.put(DRAW, draw);
        responseMap.put(RECORDS_TOTAL, recordsFetched);
        responseMap.put(RECORDS_FILTERED, recordsFetched);
        responseMap.put(DATA, orders);
    }

}

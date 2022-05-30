package com.suprun.store.entity.controller.command.impl.ajax;

import com.google.gson.Gson;
import com.suprun.store.entity.AbstractEntity;
import com.suprun.store.entity.controller.command.Command;
import com.suprun.store.entity.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.suprun.store.entity.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.jsp.PageContext.PAGE;

abstract class AbstractAjaxCommand<T extends AbstractEntity> implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    abstract Pair<Long, List<T>> filterForDatatables(int start, int length, String filterCriteria, String searchValue)
            throws ServiceException;

    abstract Pair<Long, List<T>> filterForSelect(int start, int pageSize, String filterCriteria, String searchValue)
            throws ServiceException;

    abstract Optional<T> getFromServiceById(long entityId) throws ServiceException;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String requestTypeParameter = request.getParameter(REQUEST_TYPE);
        AjaxRequestType requestType = AjaxRequestType.valueOf(requestTypeParameter);
        Map<String, Object> responseMap = new HashMap<>();

        try {
            switch (requestType) {
                case DATATABLE -> processDatatablesRequest(request, responseMap);
                case FETCH -> processFetchRequest(request, responseMap);
                case SELECT -> processSelectRequest(request, responseMap);
                default -> throw new ServiceException("Invalid request type: " + requestType);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during ajax get command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
        Gson gson = new Gson();
        return CommandResult.createJsonResult(gson.toJson(responseMap));
    }

    private void processSelectRequest(HttpServletRequest request, Map<String, Object> responseMap)
            throws ServiceException {
        String searchValue = request.getParameter(TERM);
        int page = Integer.parseInt(request.getParameter(PAGE));
        int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE));
        int start = pageSize * (page - 1);
        String filterCriteria = request.getParameter(FILTER_CRITERIA);

        Pair<Long, List<T>> pair = filterForSelect(start, pageSize, filterCriteria, searchValue);

        long recordsFetched = pair.getLeft();
        List<T> entities = pair.getRight();
        responseMap.put(RESULTS, entities);
        responseMap.put(PAGINATION_MORE, (long) page * pageSize < recordsFetched);
    }

    private void processFetchRequest(HttpServletRequest request, Map<String, Object> responseMap)
            throws ServiceException {
        long entityId = Long.parseLong(request.getParameter(ENTITY_ID));
        Optional<T> entity = getFromServiceById(entityId);
        entity.ifPresent(value -> responseMap.put(ENTITY, value));
    }

    private void processDatatablesRequest(HttpServletRequest request, Map<String, Object> responseMap)
            throws ServiceException {
        int start = Integer.parseInt(request.getParameter(PAGINATION_START));
        int length = Integer.parseInt(request.getParameter(PAGINATION_LENGTH));
        int draw = Integer.parseInt(request.getParameter(DRAW));
        String filterCriteria = request.getParameter(FILTER_CRITERIA);
        String searchValue = request.getParameter(SEARCH_VALUE);

        Pair<Long, List<T>> pair = filterForDatatables(start, length, filterCriteria, searchValue);
        long recordsFetched = pair.getLeft();
        List<T> entities = pair.getRight();

        responseMap.put(DRAW, draw);
        responseMap.put(RECORDS_TOTAL, recordsFetched);
        responseMap.put(RECORDS_FILTERED, recordsFetched);
        responseMap.put(DATA, entities);
    }
}

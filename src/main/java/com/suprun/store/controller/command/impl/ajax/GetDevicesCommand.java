package com.suprun.store.controller.command.impl.ajax;

import com.suprun.store.entity.Device;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceService;
import com.suprun.store.service.criteria.DeviceFilterCriteria;
import com.suprun.store.service.impl.DeviceServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

public class GetDevicesCommand extends AbstractAjaxCommand<Device> {

    private final DeviceService deviceService = DeviceServiceImpl.getInstance();

    @Override
    Pair<Long, List<Device>> filterForDatatables(int start, int length, String filterCriteria, String searchValue) throws ServiceException {
        return StringUtils.isEmpty(searchValue)
                ? deviceService.filter(start, length, DeviceFilterCriteria.NONE, null)
                : deviceService.filter(start, length, DeviceFilterCriteria.valueOf(filterCriteria), searchValue);
    }

    @Override
    Pair<Long, List<Device>> filterForSelect(int start, int pageSize, String filterCriteria, String searchValue) throws ServiceException {
        return deviceService.filter(start, pageSize, DeviceFilterCriteria.valueOf(filterCriteria), searchValue);
    }

    @Override
    Optional<Device> getFromServiceById(long entityId) throws ServiceException {
        return deviceService.findById(entityId);
    }

}

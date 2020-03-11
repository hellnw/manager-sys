package com.sbs.monitor.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.common.annotation.ControllerEndpoint;
import com.sbs.common.entity.SysResponse;
import com.sbs.common.utils.DateUtil;
import com.sbs.monitor.endpoint.SysHttpTraceEndpoint;
import com.sbs.monitor.entity.SysHttpTrace;

import static com.sbs.monitor.endpoint.SysHttpTraceEndpoint.SysHttpTraceDescriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("sys/actuator")
public class SysActuatorController {

    @Autowired
    private SysHttpTraceEndpoint httpTraceEndpoint;

    @GetMapping("httptrace")
    @RequiresPermissions("httptrace:view")
    @ControllerEndpoint(exceptionMessage = "请求追踪失败")
    public SysResponse httpTraces(String method, String url) {
    	SysHttpTraceDescriptor traces = httpTraceEndpoint.traces();
        List<HttpTrace> httpTraceList = traces.getTraces();
        List<SysHttpTrace> sysHttpTraces = new ArrayList<>();
        httpTraceList.forEach(t -> {
            SysHttpTrace sysHttpTrace = new SysHttpTrace();
            sysHttpTrace.setRequestTime(DateUtil.formatInstant(t.getTimestamp(), DateUtil.FULL_TIME_SPLIT_PATTERN));
            sysHttpTrace.setMethod(t.getRequest().getMethod());
            sysHttpTrace.setUrl(t.getRequest().getUri());
            sysHttpTrace.setStatus(t.getResponse().getStatus());
            sysHttpTrace.setTimeTaken(t.getTimeTaken());
            if (StringUtils.isNotBlank(method) && StringUtils.isNotBlank(url)) {
                if (StringUtils.equalsIgnoreCase(method, sysHttpTrace.getMethod())
                        && StringUtils.containsIgnoreCase(sysHttpTrace.getUrl().toString(), url))
                	sysHttpTraces.add(sysHttpTrace);
            } else if (StringUtils.isNotBlank(method)) {
                if (StringUtils.equalsIgnoreCase(method, sysHttpTrace.getMethod()))
                	sysHttpTraces.add(sysHttpTrace);
            } else if (StringUtils.isNotBlank(url)) {
                if (StringUtils.containsIgnoreCase(sysHttpTrace.getUrl().toString(), url))
                	sysHttpTraces.add(sysHttpTrace);
            } else {
            	sysHttpTraces.add(sysHttpTrace);
            }
        });
        Map<String, Object> data = new HashMap<>();
        data.put("rows", sysHttpTraces);
        data.put("total", sysHttpTraces.size());
        return new SysResponse().success().data(data);
    }
}

package com.sbs.monitor.endpoint;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import com.sbs.common.annotation.SysEndPoint;

import java.util.List;

@SysEndPoint
public class SysHttpTraceEndpoint {

    private final HttpTraceRepository repository;

    public SysHttpTraceEndpoint(HttpTraceRepository repository) {
        this.repository = repository;
    }

    public SysHttpTraceDescriptor traces() {
        return new SysHttpTraceDescriptor(this.repository.findAll());
    }

    public static final class SysHttpTraceDescriptor {

        private final List<HttpTrace> traces;

        private SysHttpTraceDescriptor(List<HttpTrace> traces) {
            this.traces = traces;
        }

        public List<HttpTrace> getTraces() {
            return this.traces;
        }
    }
}

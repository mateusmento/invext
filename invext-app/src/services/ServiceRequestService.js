import axios from "../axios";

export class ServiceRequestService {

    createServiceRequest(serviceRequestData) {
        return axios.post('/service-requests', serviceRequestData);
    }

    finishServiceRequest(serviceRequestId) {
        return axios.put(`/service-requests/${serviceRequestId}/finished`);
    }
}

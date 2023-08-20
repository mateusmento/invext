import axios from "../axios";

export class AttendantService {
    findServiceRequest(attendantId) {
        return axios.get(`/attendants/${attendantId}/service-requests`);
    }

    createAttendant(name, serviceType) {
        return axios.post('/attendants', { name, serviceType });
    }
}

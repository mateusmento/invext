import { useEffect, useRef, useState } from "react";
import debounce from 'debounce-promise';
import * as Stomp from "@stomp/stompjs";
import axios from "../axios";
import { AttendantService } from "../services/AttendantService";
import { ServiceRequestService } from "../services/ServiceRequestService";

const findAttendant = debounce((name) => {
    return axios.get(`/attendants`, { params: { name } });
}, 1000);

const attendantService = new AttendantService();
const serviceRequestService = new ServiceRequestService();

export function Attendant() {
    const [selectedAttendantId, setSelectedAttendantId] = useState();
    const [attendantName, setAttendantName] = useState('');
    const [attendants, setAttendants] = useState([]);
    const [step, setStep] = useState('identification');
    const [serviceRequests, setServiceRequests] = useState();
    const [showAttendants, setShowAttendants] = useState(false);
    const stomp = useRef();

    useEffect(() => {
        stomp.current = new Stomp.Client({
          brokerURL: 'ws://localhost:8080/websocket',
        });

        stomp.current.activate();
        return () => stomp.current.deactivate();
    }, []);
    
    async function createServiceRequest(serviceType) {
        const { data: attendant } = await attendantService.createAttendant(attendantName, serviceType);
        setSelectedAttendantId(attendant.id);
        setStep('identification');
    };

    async function updateAttendantName(name) {
        setAttendantName(name);
        if (name) {
            const { data } = await findAttendant(name);
            setAttendants(data);
            setShowAttendants(true);
        } else {
            setAttendants([]);
            setShowAttendants(false);
        }
    }

    function selectAttendant({name, id}) {
        setAttendantName(name);
        setSelectedAttendantId(id);
        setShowAttendants(false);
    }

    async function answerClient() {
      const { data } = await attendantService.findServiceRequest(selectedAttendantId);
      setServiceRequests(data);
      setStep('answer-client');
      stomp.current.subscribe(`/attendants/${selectedAttendantId}`, (serviceRequest) => {
        setServiceRequests((serviceRequests) => [...serviceRequests, JSON.parse(serviceRequest.body)]);
      });
    }

    async function finishServiceRequest(serviceRequest) {
        await serviceRequestService.finishServiceRequest(serviceRequest.id);
        const { data } = await attendantService.findServiceRequest(selectedAttendantId);
        setServiceRequests(data);
    }

    return (<>
        { step === 'identification' && (<>
            <h2>Atenda solicitações de clientes</h2>
            <div className="attendant-combo-box">
                <input
                    value={attendantName}
                    onChange={(e) => updateAttendantName(e.target.value)}
                    placeholder="Digite seu nome de usuário"
                />
                {showAttendants && (
                    <ul className="attendants">
                        {attendantName && <li className="attendant" onClick={() => setStep('create-attendant')}>Criar atendante {attendantName}</li>}
                        {attendants.map(att => (
                            <li className="attendant" onClick={() => selectAttendant(att)} key={att.id}>{att.name}</li>
                        ))}
                    </ul>
                )}
            </div>
            {selectedAttendantId && <button onClick={() => answerClient()}>Atender</button>}
        </>)}
        { step === 'create-attendant' && (<>
            <b>Criar atendante {attendantName}</b>
            <div className="service-types">
                <button onClick={() => createServiceRequest('CARD_PROBLEMS')}>Problema de Cartões</button>
                <button onClick={() => createServiceRequest('LOAN_CONTRACTING')}>Contratação de emprestimo</button>
                <button onClick={() => createServiceRequest('OTHERS')}>Outros Assuntos</button>
            </div>
            <button onClick={() => setStep('identification')}>Voltar</button>
        </>)}
        { step === 'answer-client' && (<>
            <b>Solicitações</b>
            <ul className="service-requests">
                {serviceRequests.map(serviceRequest => (
                    <li className="service-request" key={serviceRequest.id}>
                        <div>{serviceRequest.clientName}</div>
                        <button onClick={() => finishServiceRequest(serviceRequest)}>Finalizar</button>
                    </li>
                ))}
            </ul>
        </>)}
    </>);
}

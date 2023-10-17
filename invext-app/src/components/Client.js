import { useEffect, useRef, useState } from 'react';
import './App.css';
import * as Stomp from '@stomp/stompjs';
import { ServiceRequestService } from '../services/ServiceRequestService';
import { ServiceType } from '../constants/ServiceType';

const serviceRequestService = new ServiceRequestService();

function Client() {
  const [clientName, setClientName] = useState('');
  const [step, setStep] = useState('identification');
  const [serviceRequest, setServiceRequest] = useState();
  const stomp = useRef();

  useEffect(() => {
    stomp.current = new Stomp.Client({
      brokerURL: 'ws://localhost:8080/websocket',
    });

    stomp.current.activate();
    return () => stomp.current.deactivate();
  }, []);

  async function requestService(serviceType) {
    const { data: serviceRequest } = await serviceRequestService.createServiceRequest({ serviceType, clientName });

    if (serviceRequest.attendant) {
      listenAttendant(serviceRequest);
    } else {
      awaitAttendant();
    }
  }

  async function listenAttendant(serviceRequest) {
    setStep('listen-attendant');
    setServiceRequest(serviceRequest);
    stomp.current.subscribe(`/clients/${serviceRequest.clientCode}/finished`, () => {
      setServiceRequest(null);
      setStep('identification');
    });
  }

  function awaitAttendant() {
    setStep('await-attendant');
    stomp.current.subscribe(`/clients/${serviceRequest.clientCode}/accepted`, (serviceRequest) => {
      listenAttendant(JSON.parse(serviceRequest.body));
    });
  }

  return (
    <>
      { step === 'identification' && (
        <>
          <h2>Fale com um de nossos atendentes</h2>
          <input
            value={clientName}
            onChange={(e) => setClientName(e.target.value)}
            placeholder="Qual é o seu nome?"
          />
          <button onClick={() => setStep('service-type-selection')}>Próximo</button>
        </>
      )}
      { step === 'service-type-selection' && (
        <>
          <h2>Que tipo de assunto você deseja falar?</h2>
          <div className="service-types">
            <button onClick={() => requestService(ServiceType.CARD_PROBLEMS)}>Problema de Cartões</button>
            <button onClick={() => requestService(ServiceType.LOAN_CONTRACTING)}>Contratação de emprestimo</button>
            <button onClick={() => requestService(ServiceType.OTHERS)}>Outros Assuntos</button>
          </div>
          <button onClick={() => setStep('identification')}>Voltar</button>
        </>
      )}
      { step === 'listen-attendant' && (
        <>
          <h2>Você está falando com {serviceRequest.attendant.name}</h2>
        </>
      )}
      { step === 'await-attendant' && (
        <>
          <h2>No momento, nossos atendentes estão todos ocupados.</h2>
          <div>Aguarde até um atendente ficar disponível, por favor.</div>
        </>
      )}
    </>
  );
}

export default Client;

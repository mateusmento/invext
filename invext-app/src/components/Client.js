import { useEffect, useRef, useState } from 'react';
import './App.css';
import * as Stomp from '@stomp/stompjs';
import { ServiceRequestService } from '../services/ServiceRequestService';

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
      beServed(serviceRequest);
    } else {
      setStep('await-service');
      stomp.current.subscribe(`/clients/${serviceRequest.clientCode}/accepted`, (serviceRequest) => {
        beServed(JSON.parse(serviceRequest.body));
      });
    }
  }

  async function beServed(serviceRequest) {
    setStep('service');
    setServiceRequest(serviceRequest);
    stomp.current.subscribe(`/clients/${serviceRequest.clientCode}/finished`, () => {
      setServiceRequest(null);
      setStep('identification');
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
          <button onClick={() => setStep('serviceType')}>Próximo</button>
        </>
      )}
      { step === 'serviceType' && (
        <>
          <h2>Que tipo de assunto você deseja falar?</h2>
          <div className="service-types">
            <button onClick={() => requestService('CARD_PROBLEMS')}>Problema de Cartões</button>
            <button onClick={() => requestService('LOAN_CONTRACTING')}>Contratação de emprestimo</button>
            <button onClick={() => requestService('OTHERS')}>Outros Assuntos</button>
          </div>
          <button onClick={() => setStep('identification')}>Voltar</button>
        </>
      )}
      { step === 'service' && (
        <>
          <h2>Você está falando com {serviceRequest.attendant.name}</h2>
        </>
      )}
      { step === 'await-service' && (
        <>
          <h2>No momento, nossos atendentes estão todos ocupados.</h2>
          <div>Aguarde até um atendente ficar disponível, por favor.</div>
        </>
      )}
    </>
  );
}

export default Client;

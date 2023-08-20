import { useState } from 'react';
import './App.css';
import axios from 'axios';

function createServiceRequest(serviceRequestData) {
  return axios.post('http://localhost:8080/service-requests', serviceRequestData);
}

function App() {
  const [clientName, setClientName] = useState('');
  const [step, setStep] = useState('clientName');
  const [serviceRequest, setServiceRequest] = useState();

  async function requestService(serviceType) {
    const { data: serviceRequest } = await createServiceRequest({ serviceType, clientName });
    if (serviceRequest.attendant) {
      setStep('service');
    } else {
      setStep('await-service');
    }
    setServiceRequest(serviceRequest);
  }

  return (
    <div className="App">
      <header>
        <h1>INVEXT</h1>
        <button>Sou atendente</button>
      </header>
      <main>
        { step === 'clientName' && (
          <>
            <h2>Fala com um de nossos atendentes</h2>
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
            <button onClick={() => setStep('clientName')}>Voltar</button>
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
      </main>
    </div>
  );
}

export default App;

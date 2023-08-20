import { useState } from 'react';
import './App.css';

function App() {
  const [clientName, setClientName] = useState('');
  const [serviceType, setServiceType] = useState();
  const [step, setStep] = useState('clientName');

  function selectServiceType(serviceType) {
    setServiceType(serviceType);
    setStep('service');
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
            <div class="service-types">
              <button onClick={() => selectServiceType('CARD_PROBLEMS')}>Problema de Cartões</button>
              <button onClick={() => selectServiceType('LOAN_CONTRACTING')}>Contratação de emprestimo</button>
              <button onClick={() => selectServiceType('OTHERS')}>Outros Assuntos</button>
            </div>
            <button onClick={() => setStep('clientName')}>Voltar</button>
          </>
        )}
      </main>
    </div>
  );
}

export default App;

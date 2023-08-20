import { Outlet, useLocation } from 'react-router-dom';
import './App.css';

export function Root() {
  const location = useLocation();

  return (
    <div className="App">
      <header>
        <h1>INVEXT</h1>
        { location.pathname === '/client'
          ? <a className="button" href="/attendant">Sou atendente</a>
          : <a className="button" href="/client">Sou cliente</a>
        }
      </header>
      <main>
        <Outlet/>
      </main>
    </div>
  );
}

import { Outlet } from 'react-router-dom';
import './App.css';

export function Root() {
  return (
    <div className="App">
      <header>
        <h1>INVEXT</h1>
        <a className="button" href="/attendant">Sou atendente</a>
        <a className="button" href="/client">Sou cliente</a>
      </header>
      <main>
        <Outlet/>
      </main>
    </div>
  );
}

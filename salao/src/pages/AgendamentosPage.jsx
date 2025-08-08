// Local do arquivo: src/pages/AgendamentosPage.jsx (Versão 3 - Modal Refatorado)

import React, { useState, useEffect, useCallback } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'moment/locale/pt-br';
import './AgendamentosPage.css';

import { 
    getAllAgendamentos, 
    createAgendamento, 
    getAllClientes, 
    getAllServicos 
} from '../services/apiService.js';

moment.locale('pt-br');
const localizer = momentLocalizer(moment);

const messages = {
    allDay: 'Dia Inteiro', previous: '<', next: '>', today: 'Hoje',
    month: 'Mês', week: 'Semana', day: 'Dia', agenda: 'Agenda',
    date: 'Data', time: 'Hora', event: 'Evento', noEventsInRange: 'Não há agendamentos neste período.',
    showMore: total => `+ ver mais (${total})`
};

// ✅ NOVO COMPONENTE: Um modal genérico e reutilizável
const Modal = ({ children, isOpen, onClose }) => {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                {children}
            </div>
        </div>
    );
};

function AgendamentosPage() {
    const [events, setEvents] = useState([]);
    const [clientes, setClientes] = useState([]);
    const [servicos, setServicos] = useState([]);
    
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showModal, setShowModal] = useState(false);
    
    const [slotInfo, setSlotInfo] = useState(null);
    const [selectedCliente, setSelectedCliente] = useState('');
    const [selectedServicos, setSelectedServicos] = useState([]);
    const [observacoes, setObservacoes] = useState('');
    const [selectedTime, setSelectedTime] = useState('12:00'); 

    const fetchData = useCallback(async () => {
        // ... (código do fetchData permanece o mesmo)
        setLoading(true);
        setError(null);
        try {
            const [agendamentosRes, clientesRes, servicosRes] = await Promise.all([
                getAllAgendamentos(),
                getAllClientes(),
                getAllServicos()
            ]);

            const formattedEvents = agendamentosRes.data.map(ag => ({
                id: ag.id,
                title: `${ag.cliente.nome} - ${ag.servicos.map(s => s.nome).join(', ')}`,
                start: new Date(ag.dataHora),
                end: moment(ag.dataHora).add(1, 'hours').toDate(),
                resource: ag
            }));
            
            setEvents(formattedEvents);
            setClientes(clientesRes.data);
            setServicos(servicosRes.data);

        } catch (err) {
            console.error("Falha ao carregar dados:", err);
            setError("Não foi possível carregar os dados da agenda. Verifique a conexão com a API e se o backend está no ar.");
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchData();
    }, [fetchData]);

    const handleSelectSlot = useCallback((slot) => {
        setSlotInfo(slot);
        setSelectedTime(moment(slot.start).format('HH:mm'));
        setShowModal(true);
    }, []);

    const handleServicoChange = (event) => {
        const { options } = event.target;
        const selectedIds = Array.from(options)
            .filter(option => option.selected)
            .map(option => option.value);
        setSelectedServicos(selectedIds);
    };

    const handleSubmit = async (e) => {
        // ... (código do handleSubmit permanece o mesmo)
        e.preventDefault();
        if (!selectedCliente || selectedServicos.length === 0 || !slotInfo) return;

        const [hours, minutes] = selectedTime.split(':');
        const finalDateTime = moment(slotInfo.start).set({ hours, minutes }).format('YYYY-MM-DDTHH:mm:ss');

        const agendamentoData = {
            clienteId: selectedCliente,
            servicoIds: selectedServicos,
            dataHora: finalDateTime,
            observacoes
        };

        try {
            await createAgendamento(agendamentoData);
            setShowModal(false);
            setSelectedCliente('');
            setSelectedServicos([]);
            setObservacoes('');
            fetchData(); 

        } catch (error) {
            console.error("Erro ao criar agendamento:", error);
            alert("Falha ao criar agendamento. Verifique os dados e tente novamente.");
        }
    };
    
    if (loading) return <p>Carregando...</p>;
    if (error) return <div className="page-error">{error}</div>;

    return (
        <div className="page-container">
            <h1 className="page-title">Agenda de Clientes</h1>

            <div className="calendar-container">
                <Calendar
                    localizer={localizer}
                    events={events}
                    startAccessor="start"
                    endAccessor="end"
                    style={{ height: '100%' }}
                    selectable
                    onSelectSlot={handleSelectSlot}
                    messages={messages}
                />
            </div>

            {/* ✅ CÓDIGO MAIS LIMPO: Usamos nosso novo componente Modal */}
            <Modal isOpen={showModal} onClose={() => setShowModal(false)}>
                <form onSubmit={handleSubmit} className="modal-form">
                    <div className="modal-header">
                        <h3>Novo Agendamento</h3>
                        <p>Para o dia: <strong>{slotInfo && moment(slotInfo.start).format('DD/MM/YYYY')}</strong></p>
                    </div>
                    
                    <div className="form-group">
                        <label>Hora do Agendamento</label>
                        <input 
                            type="time" 
                            value={selectedTime} 
                            onChange={(e) => setSelectedTime(e.target.value)} 
                            required 
                        />
                    </div>
                    
                    <div className="form-group">
                        <label>Cliente</label>
                        <select value={selectedCliente} onChange={(e) => setSelectedCliente(e.target.value)} required>
                            <option value="">-- Selecione um Cliente --</option>
                            {clientes.map(c => <option key={c.id} value={c.id}>{c.nome}</option>)}
                        </select>
                    </div>
                    
                    <div className="form-group">
                        <label>Serviços (segure Ctrl/Cmd para selecionar vários)</label>
                        <select multiple value={selectedServicos} onChange={handleServicoChange} required>
                            {servicos.map(s => <option key={s.id} value={s.id}>{s.nome}</option>)}
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Observações</label>
                        <textarea value={observacoes} onChange={(e) => setObservacoes(e.target.value)} />
                    </div>

                    <div className="modal-actions">
                        <button type="button" className="btn-secondary" onClick={() => setShowModal(false)}>Cancelar</button>
                        <button type="submit" className="btn-primary">Agendar</button>
                    </div>
                </form>
            </Modal>
        </div>
    );
}

export default AgendamentosPage;
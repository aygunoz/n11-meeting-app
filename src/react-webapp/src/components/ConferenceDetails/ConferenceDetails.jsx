import './ConferenceDetails.css'

function formatTime(time, dayTime) {
    const [hours, minutes] = time.split(':');
    return `${hours}:${minutes} ${dayTime}`;
}
function ConferenceDetails({tracks}) {
    if (!tracks || tracks.length === 0) {
        return <div>No schedule data available</div>;
    }

    return (
        <div className="conference-schedule">

            {tracks.map((track, index) => (
                <div key={index} className="track">
                    <h2>Track {index + 1}</h2>
                    {Array.isArray(tracks) && track.morningTalks.map((event, eventIndex) => (
                        <div key={eventIndex} className="event">
                            <span className="time">{formatTime(event.startTime, 'AM')}</span>
                            <span className="title">{event.title}</span>
                            <span className="duration">{event.duration > 0 ? `${event.duration}min` : 'Networking Event'}</span>
                        </div>
                    ))}
                    {Array.isArray(tracks) && track.afternoonTalks.map((event, eventIndex) => (
                        <div key={eventIndex} className="event">
                            <span className="time">{formatTime(event.startTime, 'PM')}</span>
                            <span className="title">{event.title}</span>
                            <span className="duration">{event.duration > 0 ? `${event.duration}min` : 'Networking Event'}</span>
                        </div>
                    ))}
                </div>
            ))}
        </div>
    );
}

export default ConferenceDetails

import {useState} from 'react'
import {createTalk} from "../../services/TalkService.jsx";

function PresentationApply() {
    const [meetingTitle, setMeetingTitle] = useState('')
    const [meetingDuration, setMeetingDuration] = useState(30)

    const handleApplyButtonClick = () => {
        createTalk(meetingTitle, meetingDuration).then(r => {
            setMeetingTitle('');
        });
    }

    return (
        <>
            <div>
                <input
                    type="text"
                    placeholder="Meeting Subject"
                    value={meetingTitle}
                    onChange={(e) => setMeetingTitle(e.target.value)}
                />
                <div className="select-container">
                    <select
                        id="meetingDuration"
                        value={meetingDuration}
                        onChange={(e) => setMeetingDuration(Number(e.target.value))}
                    >
                        <option value={30}>30 minutes</option>
                        <option value={45}>45 minutes</option>
                        <option value={60}>60 minutes</option>
                    </select>
                </div>
            </div>
            <div className="card">
                <button onClick={handleApplyButtonClick}>
                    Apply
                </button>
            </div>
            <a href="mailto:aygunozdemir1@gmail.com" target="_blank">
                <p className="read-the-docs">
                    Click on to learn more and contact to support
                </p>
            </a>
        </>
    )
}

export default PresentationApply

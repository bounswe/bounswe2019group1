import React from 'react';
import Marker from 'react-icons/lib/ti/bookmark';

export default function Annotation({ annotation, showAnnotation }) {
    return(
        <div className="annotation"
             style={{left: annotation.target.x, top: annotation.target.y}}>
            <Marker/>
            {annotation.display &&
            <div className="annotation-title">{annotation.body.text}</div>
            }
        </div>
    );
}

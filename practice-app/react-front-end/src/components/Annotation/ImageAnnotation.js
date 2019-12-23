import React, {Component} from 'react';
import Annotation from 'react-image-annotation'
import Input from '@material-ui/core/Input';
import Button from '@material-ui/core/Button';
import {TiTimes as CloseButton} from "react-icons/ti";
import swal from "sweetalert";
import {createAnnotation} from "service/annotation.service.js";
import {
    RectangleSelector,
} from 'react-image-annotation/lib/selectors'


class ImageAnnotation extends Component {
    constructor(props) {
        super(props);

        this.state = {
            annotation: {},
            annotations: [],
        }
        this.createImageAnnotation = this.createImageAnnotation.bind(this);
    }

    onChange = (annotation) => {
        this.setState({annotation})
    }

    onSubmit = (annotation) => {
        const {geometry, data} = annotation
        this.setState({
            annotation: {},
            annotations: this.state.annotations.concat({
                geometry,
                data: {
                    ...data,
                    id: Math.random()
                }
            })
        })
        this.createImageAnnotation(data.text, parseInt(geometry.x), parseInt(geometry.y), parseInt(geometry.height), parseInt(geometry.width))

    }

    createImageAnnotation(text, x_selected, y_selected, height, width) {
        const values = {
            body: {
                type: "TextualBody",
                purpose: "tagging",
                value: text
            },
            target: {
                source: "http://www.khajiittraders.tk/article/" + this.props.article_id + "/",
                type: "Image",
                image_id: "http://www.khajiittraders.tk/article/" + this.props.article_id + "/image1#xywh=" + x_selected + "," + y_selected + "," + width + "," + height
            },
            motivation: "Referral"
        };
        createAnnotation(values)
            .then(res => (res.status === 200 ? res : null))
            .catch(error => {
                swal("Oops: ", error.message, "error");
            });
    }

    render() {
        const {src} = this.props;
        return (
            <Annotation
                src={src}
                alt='Article Image'
                annotations={this.state.annotations}
                type={RectangleSelector.TYPE}
                value={this.state.annotation}
                onChange={this.onChange}
                onSubmit={this.onSubmit}
            />
        )
    }
}

export default ImageAnnotation;

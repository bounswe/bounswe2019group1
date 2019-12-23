import React, {Component} from "react";
import Paragraph from "react-annotated-paragraph";
import {TiTimes as CloseButton} from "react-icons/ti";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";
import {createAnnotation} from "service/annotation.service.js";
import swal from "sweetalert";

export class TextAnnotation extends Component {
    constructor(props) {
        super(props);

        this.state = {
            annotationInput: {
                open: false,
                text: "",
                start: 0,
                end: 0,
                boxX: 0,
                boxY: 0
            }
        };
        this.createTextAnnotation = this.createTextAnnotation.bind(this);
        this.showSelectedText = this.showSelectedText.bind(this);
        this.getCaretCharacterOffsetWithin = this.getCaretCharacterOffsetWithin.bind(
            this
        );
    }

    getCaretCharacterOffsetWithin(element) {
        var caretOffset = 0;
        var document = element.ownerDocument || element.document;
        var win = document.defaultView || document.parentWindow;
        var selection;
        if (typeof win.getSelection != "undefined") {
            selection = win.getSelection();
            if (selection.rangeCount > 0) {
                var range = win.getSelection().getRangeAt(0);
                var preCaretRange = range.cloneRange();
                preCaretRange.selectNodeContents(element);
                preCaretRange.setEnd(range.endContainer, range.endOffset);
                caretOffset = preCaretRange.toString().length;
            }
        } else if (
            (selection = document.selection) &&
            selection.type !== "Control"
        ) {
            var textRange = selection.createRange();
            var preCaretTextRange = document.body.createTextRange();
            preCaretTextRange.moveToElementText(element);
            preCaretTextRange.setEndPoint("EndToEnd", textRange);
            caretOffset = preCaretTextRange.text.length;
        }
        return caretOffset;
    }

    showSelectedText(e) {
        var text = "";
        if (window.getSelection) {
            text = window.getSelection();
        } else if (document.getSelection) {
            text = document.getSelection();
        } else if (document.selection) {
            text = document.selection.createRange().text;
        }
        const caretPos = this.getCaretCharacterOffsetWithin(e.target);
        const length_ = text.focusOffset - text.anchorOffset;
        const length = length_ > 0 ? length_ : -length_;
        const start = caretPos - length;

        if (text.toString() !== "")
            this.setState({
                annotationInput: {
                    ...this.props.annotationInput,
                    open: true,
                    start,
                    end: start + length,
                    boxX: e.nativeEvent.offsetX,
                    boxY: e.nativeEvent.offsetY
                }
            });
    }

    /*
 * 'text': the whole text, 'Hello World!'
 * 'annotation': { offset: 6, length: 5, tooltip: "Welt" }
 */
    mySimpleRenderer = (text, annotation) => {
        let explanation = annotation.tooltip
        let highlighted = text.substr(annotation.offset, annotation.length);
        return {
            explanation,
            highlighted
        }
    }

    createTextAnnotation() {
        const values = {
            body: [{
                type: "TextualBody",
                purpose: "tagging",
                value: this.state.annotationInput.text
            }],
            target: {
                source: "http://www.khajiittraders.tk/article/" + this.props.article_id + "/",
                selector: {
                    refinedBy: {
                        type: "TextPositionSelector",
                        start: this.state.annotationInput.start,
                        end: this.state.annotationInput.end
                    },
                    type: "FragmentSelector",
                    value: "xpointer(/doc/body/section[2]/para[1])"
                },
                type: "text",
            },
            motivation: "Commenting"
        };
        createAnnotation(values)
            .then(res => (res.status === 200 ? res : null))
            .then(() => {
                window.location.reload();
                swal("Good job!", "Annotation is successfully added.", "Success");
            })
            .catch(error => {
                swal("Oops: ", error.message, "error");
            });
    }
    // createTooltip = (annotation) => {
    //     var tooltip = "";
    //     annotation.body.forEach((item) => tooltip += item.value + " added by "  +annotation.creator.name + "," + annotation.created)
    //     return tooltip;
    // };
    render() {
        const {text, showAnnotations, annotations} = this.props;
        const {annotationInput} = this.state;
        return (
            <div onMouseUp={this.showSelectedText}>
                <div className="annotated-description">
                    {text && annotationInput.open && (
                        <div
                            className="annotation-input"
                            style={{
                                left: annotationInput.boxX,
                                top: annotationInput.boxY + 70
                            }}
                        >
                            <CloseButton
                                onClick={e =>
                                    this.setState({
                                        annotationInput: {...annotationInput, open: false}
                                    })
                                }
                            />
                            <Input
                                type="text"
                                value={annotationInput.text}
                                onChange={e =>
                                    this.setState({
                                        annotationInput: {
                                            ...annotationInput,
                                            text: e.target.value
                                        }
                                    })
                                }
                            />
                            <Button onClick={e => this.createTextAnnotation()}>Create</Button>
                        </div>
                    )}

                    {text && showAnnotations && annotations.length !== 0 ? (
                        <Paragraph
                            paragraph={{
                                text: text,
                                annotations: annotations.map(a => ({
                                    offset: a.target.selector.refinedBy.start,
                                    length: a.target.selector.refinedBy.end - a.target.selector.refinedBy.start,
                                    tooltip: a.body[0].value + " added by "  +a.creator.name + "," + a.created,
                                }))
                            }}
                            tooltipRenderer={this.mySimpleRenderer}
                        />
                    ) : (
                        <div>{text}</div>
                    )}
                </div>
            </div>
        );
    }
}

export default TextAnnotation;
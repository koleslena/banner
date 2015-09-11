/**
 * Created by elenko on 23.06.15.
 */
var Item = React.createClass({
    render: function () {
        return (
            <div className="item">
            <div>{this.props.id}. {this.props.name}. {this.props.reff}.</div>
        </div>
        );
    }
});

var NavigateLink = React.createClass({
    render: function () {
        return (
            <button className="navigateLink" onClick={this.props.onClick}>{this.props.name}</button>
        );
    }
});

var PagePanel = React.createClass({
    render: function () {
        return (
            <div className="pagePanel">Page {this.props.pageNumber + 1}/ TotalPages {this.props.totalPages}</div>
        );
    }
});

var ItemPagingList = React.createClass({
    getInitialState: function () {
        var selfLink = "http://localhost:8080/bn/banner/";
        return {data: {content: [], selfLink: selfLink, nextLink: null, prevLink: null, paging: {pageNumber: 0, totalPages: 1}}};
    },

    loadData: function (url) {
        $.ajax({
            url: url,
            dataType: 'json',
            success: function (data) {
                var next = data.last ? data.number : data.number + 1;
                var prev = data.first ? data.number: data.number - 1;
                var oldPar = "?page=" + data.number;
                var nextPar = "?page=" + next;
                var prevPar = "?page=" + prev;

                this.setState({
                    data: {
                        content: data.content,
                        selfLink: url,
                        nextLink: data.first ? url + nextPar: url.replace(oldPar, nextPar),
                        prevLink: data.first ? url + prevPar: url.replace(oldPar, prevPar),
                        paging: {
                            pageNumber: data.number,
                            totalPages: data.totalPages
                        }
                    }
                });
            }.bind(this),
            error: function (xhr, status, err) {
                //console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },

    nextData: function () {
        this.loadData(this.state.data.nextLink);
    },

    prevData: function () {
        this.loadData(this.state.data.prevLink);
    },

    componentDidMount: function () {
        this.loadData(this.state.data.selfLink);
    },

    render: function () {
        var itemNodes = this.state.data.content.map(function (item) {
            return (
                <Item name={item.name} id={item.id} reff={item.ref}></Item>
            );
        });
        var nextLink = this.state.data.nextLink ? (<NavigateLink name="next" onClick={this.nextData}></NavigateLink>) : '';
        var prevLink = this.state.data.prevLink ? (<NavigateLink name="prev" onClick={this.prevData}></NavigateLink>) : '';
        return (
            <div className="itemPagingList">
                {itemNodes}
                <PagePanel pageNumber={this.state.data.paging.pageNumber} totalPages={this.state.data.paging.totalPages}></PagePanel>
                {prevLink}
                {nextLink}
            </div>
        );
    }
});


      React.render(
        <ItemPagingList/>,
        document.getElementById('content')
    );

/**
 * Created by elenko on 11.09.15.
 */
var UploadImageForm = React.createClass({
    getInitialState: function() {
        return {
            myFileName: "",
            myFileHandle: {}
        };
    },

    handleChange: function(event) {
        console.log("handleChange() fileName = " + event.target.files[0].name);
        console.log("handleChange() file handle = " + event.target.files[0]);
        this.setState( {myFileName: event.target.files[0].name} );
        this.setState( {myFileHandle: event.target.files[0]} );

        this.handleChangeImageUrlFromFile(event.target.files[0]);
    },

    handleChangeImageUrlFromData: function(data) {
        var imageUrl = "data:image/png;base64," + data + "";

        this.setState( {fileurl: imageUrl} );
    },

    handleChangeImageUrlFromFile: function(file) {
        var URL = window.URL || window.webkitURL,
            imageUrl;

        if (URL) {
            imageUrl = URL.createObjectURL(file);

            this.setState( {fileurl: imageUrl} );
        }
    },

    handleSubmit: function(e) {
        e.preventDefault();
        console.log("INSIDE: handleSubmit()");
        console.log("fileName = " + this.state.myFileName);
        console.log("this.state.myFileHandle = " + this.state.myFileHandle);
        console.log("this.state.fileurl = " + this.state.fileurl);

        _self = this;

        if (this.state.myFileHandle) {
            console.log("INSIDE if test myFileHandle.length");
            var file = this.state.myFileHandle;
            var name = this.state.myFileName;

            var fd = new FormData();
            fd.append( 'content', this.refs.file.getDOMNode().files[0] );
            fd.append( 'name', this.refs.name.getDOMNode().value);
            fd.append( 'ref', this.refs.reff.getDOMNode().value);

            jQuery.ajax({
                url: 'http://localhost:8080/bn/banner/save',
                type : 'POST',
                data: fd,
                processData: false,
                contentType: false,
                success: function(data){
                    _self.handleChangeImageUrlFromData(data.img);
                }
            });
        }

    },

    render: function() {
        return  (
            <div className="uploadImageForm">
                <Image img={this.state.fileurl}></Image>

                <form ref="uploadForm" onSubmit={this.handleSubmit}>
                    Name: <input ref="name" type="text" id="name"/>
                    Url: <input ref="reff" type="text" id="ref"/>
                    Image: <input ref="file" type="file" onChange={this.handleChange} id="profilePhotoFileUpload" />
                    <input type="submit" value="Post" />
                </form>
            </div>
        );
    }
});

var Image = React.createClass({
    render: function () {
        return (
            <div className="image">
                <img src={this.props.img} />
            </div>
        );
    }
});


React.render(
    <UploadImageForm/>,
    document.getElementById('saveBanner')
);


import * as React from 'react';
import { Fragment } from 'react';
import { connect } from 'react-redux';
import { itemsFetchData, itemSelected } from '../../actions/items';
import SimpleList from '../common/SimpleList';

import {Button} from 'react-bootstrap';
import { ClipLoader } from 'react-spinners';

export interface IItemProps {
    items: any[];
    isLoading: boolean;
    hasErrored: boolean;
    fetchData: (url: string, embeddedArray: string) => void;
    selectItem: (item: any) => void;
    handleAdd: () => void;
    show: (s: any) => JSX.Element;
    fetchFrom: string;
    embeddedArray: string;
}

class ListWithControllers extends React.Component<IItemProps,any> {

    public componentDidMount() {
        this.props.fetchData(this.props.fetchFrom, this.props.embeddedArray);
    }

    public render() {
        if (this.props.hasErrored) {
            return <p>Oops! Houve um erro ao carregar os dados.</p>;
        }
        if (this.props.isLoading) {
            return <ClipLoader/>;
        }

        return (
            <Fragment>
                <Button onClick={this.props.handleAdd}>Adicionar</Button>
                <SimpleList<any>
                    list={this.props.items}
                    show={this.props.show}
                />
            </Fragment>
        );
    }
}

const mapStateToProps = (state: any) => {
    return {
        items: state.items,
        hasErrored: state.itemsHasErrored,
        isLoading: state.itemsIsLoading,
        itemSelected: state.itemSelected,
    };
};

const mapDispatchToProps = (dispatch: any) => {
    return {
        fetchData: (url: string, embeddedArray: string) => dispatch(itemsFetchData(url, embeddedArray)),
        selectItem: (item: any) => dispatch(itemSelected(item))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(ListWithControllers);
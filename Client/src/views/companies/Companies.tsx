import * as React from 'react';
import {connect} from "react-redux";
import {companiesFetchData} from "../../actions/companiesActions";
import {ICompany} from "../../reducers/company";
import {Fragment} from "react";

export interface ICompanyProps {
    companies: ICompany[],
    fetchData: () => void,
    hasErrored: boolean,
    isLoading: boolean,
}
class Companies extends React.Component<ICompanyProps,{}> {

    constructor(props: ICompanyProps) {
        super(props);

    }

    public componentDidMount() {
        this.props.fetchData();
    }

    public render(){
        if (this.props.hasErrored) {
            return <p>Sorry! There was an error loading the items.</p>;
        }
        if (this.props.isLoading) {
            return <p>Loading...</p>;
        }

        return (

            <Fragment>
                <ul>
                    {
                        this.props.companies && this.props.companies.map(c => (
                            <li key={c.id}>
                                <h2>{c.name}</h2>
                                <p>Located: {c.city}, {c.address}</p>
                                <p>Contact: {c.email} / {c.phone}</p>
                                <p>Fax: {c.fax}</p>

                            </li>
                        ))
                    }
                </ul>
            </Fragment>
        );

    }
}

const mapStateToProps = (state: any) => {

    return {
        companies: state.companies,
        hasErrored: state.companiesHasErrored,
        isLoading: state.companiesIsLoading,
        modalOpen: state.modalStatusChanged,
    };
};

const mapDispatchToProps = (dispatch: any) => {
    return {
        fetchData: () => dispatch(companiesFetchData()),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Companies);
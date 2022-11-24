package forms;

import modules.Car;

import java.time.LocalDate;

public class CarContract {

    private String carContractString;


    public String getCarContractString() {
        return carContractString;
    }

    public static String getCarContract (String owner, String model, LocalDate startDate, LocalDate endDate) {
            return ("\"PLACEHOLDER CONTRACT TEXT, GATHERED FROM https://templatelab.com/car-rental-agreement/#google_vignette\n\n" +
                    "This  Car  Rental  Agreement is  made  and  entered  into between\n" +
                    "you (Renter) and " + owner + ", (Owner). Owner and Renter may also be referred to " +
                    "as Party in the singular and 'Parties' in the plural. This Agreement is subject to the following " +
                    "terms and conditions:\n" +
                    "Rental Vehicle\n" +
                    "Owner hereby agrees to rent to Renter the following vehicle:\n" +
                    "Model: " + model + "\n" +
                    "Rental Period\n" +
                    "Owner agrees to rent Vehicle to Renter for the following period:\n" +
                    "Start Date: "+ startDate +  " End Date: " + endDate +  "\t\n" +
                    "The Parties agrees that this Agreement terminates upon the End Date specified above. Notwithstanding " +
                    "anything to the contrary in this Agreement or any Exhibits, either Party may terminate this Agreement " +
                    "prior to the End Date with at least one (1) day notice. If this Agreement is terminated prior to the " +
                    "End Date, the Parties will work together to determine whether a refund of Rental Fees is necessary.\\\n" +
                    "Mileage Limit\\n" +
                    "Renter will obey the following mileage limit for the Vehicle:\n" +
                    "[   ] No mileage limit[   ]\n" +
                    "Rental Fees\n" +
                    "The Renter hereby agrees to pay the Owner for use of the Vehicle as follows:\\\n" +
                    "Fuel: Renter shall pay / is not required to pay for the use of fuel. Excess Mileage: ______ per mile\\n" +
                    "Deposit: _______. Owner shall retain this deposit to be used, in the event of loss of or damage " +
                    "to the Vehicle during the term of this Agreement, to defray fully or partially the cost of " +
                    "necessary repairs or replacement. In the absence of damage or loss, said deposit shall be credited " +
                    "toward payment of the rental fee and any excess shall be returned to the Renter.\\n" +
                    "Existing Damage to Vehicle\n" +
                    "The Parties acknowledge the existing damage to the Vehicle as notated below:\\n" +
                    "____________________________________________\n" +
                    "____________________________________________\n" +
                    "____________________________________________\n" +
                    "____________________________________________\n" +
                    "____________________________________________\n" +
                    "____________________________________________\n" +
                    "____________________________________________\n" +
                    "Insurance\n" +
                    "The Renter hereby warrants to Owner that Renter possess car insurance that covers personal injury to" +
                    " Renter or  other persons as well as the Vehicle and the property of others.\\\n" +
                    "Indemnity\n" +
                    "Regardless of insurance coverage, Renter shall fully indemnify the Owner for any loss, damage, and " +
                    "legal actions, including reasonable attorneys fees that Owner suffers due to Renter’s use of Vehicle " +
                    "during the term of this Agreement, including but not limited to, damage to the Vehicle, damage to the " +
                    "property of others, injury to Renter, and injury to others. This provision survives the termination of " +
                    "this Agreement.\n" +
                    "Owner Warranty\n" +
                    "The Owner represents that to the best of his knowledge and belief that the Vehicle is in sound and " +
                    "safe condition and free of any known faults or defects that would affect its safe operation under " +
                    "normal use.\n" +
                    "Renter Warranties\n" +
                    "The Renter agrees that Renter will not (a) use the Vehicle to carry any passengers other than Renter; " +
                    "(b) allow any other person to operate the Vehicle; (c) operate the Vehicle in violation of any laws or " +
                    "for an illegal purpose and that if Renter does, Renter is responsible for all associated, tickets, fines," +
                    " and fees; (d) use the Vehicle to push or tow another vehicle; (e) use the Vehicle for any race or " +
                    "competition; (f) operate the vehicle in a negligent manner.\n" +
                    "Arbitration\n" +
                    "In the event that the Parties cannot amicably resolve a dispute or damage claim resulting from this " +
                    "Agreement, the Parties agree to resolve any such dispute or damage claim by arbitration. " +
                    "The arbitration proceeding shall be conducted in [City], [State], in accordance with the rules of " +
                    "the American Arbitration Association then in effect with one (1) arbitrator to be selected by mutual " +
                    "agreement of the Parties.  If the Parties cannot agree on an arbitrator,  then the American Arbitration " +
                    "Association shall select an arbitrator from the National Panel of Arbitrators. The laws  of the State " +
                    "of [State] in the United States shall apply to the arbitration proceedings. The Parties agree that the " +
                    "arbitrator cannot award punitive damages to either Party and agree to be bound by the  arbitrator’s  " +
                    "findings. Judgment upon the award rendered by the arbitrator may be entered in any court having jurisdiction.\\\n" +
                    "Disputes and Governing Law.\n" +
                    "The laws of the State of [State] in the United States without regard to any conflict of law principles " +
                    "govern this Agreement. No action, arising out of the transactions under this Agreement may be brought " +
                    "by either Party more  than one year after the cause of action has accrued.\n" +
                    "General\n" +
                    "This Agreement, including all Exhibit(s), constitutes the entire agreement between the Parties in " +
                    "connection with the subject matter hereof and supersedes all agreements, proposals, representations " +
                    "and other understandings, oral or written, of the Parties and any current or subsequent purchase " +
                    "order(s) provided by Affiliate. No alteration or modification of this Agreement or any Exhibits shall " +
                    "be valid unless made in writing and signed by an authorized Affiliate of each Party. The waiver by either" +
                    " Party of a breach of any provision of the Agreement shall not operate or be construed as a waiver of " +
                    "any subsequent breach and any waiver must be in writing and signed by an authorized Affiliate of the " +
                    "Parties hereto. If any provision of this Agreement is held to be invalid or unenforceable, " +
                    "the remaining provisions shall continue in full force and effect. Any notice or other communication" +
                    " required or permitted hereunder shall be given in writing to the other Party at the address stated " +
                    "above, or at such other address as shall be given by either Party to the other in writing. Any terms of" +
                    " this Agreement which by their nature extend beyond its termination remain in effect until fulfilled," +
                    " and apply to respective successors and rightful assignees.\n");
        }
    }


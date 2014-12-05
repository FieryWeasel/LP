//
//  ViewController.m
//  DroitDeVote
//
//  Created by iem on 04/12/2014.
//  Copyright (c) 2014 iem. All rights reserved.
//

#import "ViewController.h"
#import "Person.h"

@interface ViewController ()

@property (weak, nonatomic) IBOutlet UILabel *labelAnswer;
@property (weak, nonatomic) IBOutlet UILabel *ageLabel;
@property (nonatomic) Person *person;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    _person = [[Person alloc] initWithAge:0];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)changedValue:(id)sender {
    UISlider *slider = (UISlider *)sender;
    
    [_person setAge:[slider value]];
    
    [_ageLabel setText:[NSString stringWithFormat:@"Your age : %d", [_person age]]];
    
    if([_person canLegallyVote]){
        [_labelAnswer setText:@"You can vote."];
    }else{
        [_labelAnswer setText:@"You can't vote."];
        
    }
}
- (IBAction)marryMeButton:(id)sender {
    Person *person1 = [Person createPersonWithAge:25];
    Person *person2 = [Person createPersonWithAge:25];

    [person1 setSpouse:person2];
    [person2 setSpouse:person1];
    
}


@end
